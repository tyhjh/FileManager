package com.yorhp.filemanager.service.impl;

import com.yorhp.filemanager.domin.App;
import com.yorhp.filemanager.domin.MyFile;
import com.yorhp.filemanager.enums.ResultEnum;
import com.yorhp.filemanager.exception.MlException;
import com.yorhp.filemanager.repository.MyFileRepository;
import com.yorhp.filemanager.service.FileService;
import com.yorhp.filemanager.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private final static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    private static double MINI_FILE_SIZE = 0.3;
    private static int MINI_FILE_WIDTH = 300;
    private static String RECYCLE_TAG = "回收站";
    @Autowired
    MyFileRepository myFileRepository;

    @Override
    @Transactional
    public MyFile savePic(MyFile myFile, MultipartFile file) {
        try {
            File newFile = FileUtil.saveFile(file, App.appDir);
            myFile.setCreateTime(System.currentTimeMillis());
            myFile.setFileName(newFile.getName());
            myFile.setFilePath(newFile.getPath());
            myFile.setFileSize(FileUtil.getFileSize(newFile));
            myFile.setFileType(FileUtil.getFileType(newFile.getName()));
            myFile.setFileUrl(App.domainName + URLEncoder.encode(myFile.getFileName(), "UTF-8"));
            if (FileUtil.isPic(newFile.getName())) {
                File miniFile = FileUtil.compressPic(newFile, App.appDirMini + "mini_" + newFile.getName(), MINI_FILE_SIZE, MINI_FILE_WIDTH);
                myFile.setMiniFileSize(FileUtil.getFileSize(miniFile));
                myFile.setFileMiniUrl(App.domainName + "mini/" + URLEncoder.encode(miniFile.getName(), "UTF-8"));
            } else {
                myFile.setMiniFileSize(myFile.getFileSize());
                myFile.setFileMiniUrl(myFile.getFileUrl());
            }

            myFileRepository.save(myFile);
            return myFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new MlException(ResultEnum.LACK_VALUE);
    }

    @Override
    public String saveFile(MultipartFile file) {
        String path=null;
        File newFile = FileUtil.saveFile(file, App.appDir);
        try {
            path=App.domainName + URLEncoder.encode(newFile.getName(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return path;
    }

    @Override
    @Transactional
    public List<MyFile> getFiles(String userId) {
        List<MyFile> myFiles = myFileRepository.findMyFilesByUserIdAndFileTagNotAndCanReadOrderByCreateTimeAsc(userId, RECYCLE_TAG, true);
        return myFiles;
    }

    @Override
    public void deleteFile(Long fileId) {
        MyFile myFile = myFileRepository.findFirstByMyFileId(fileId);
        if (myFile == null)
            return;
        //删除回收站的文件
        if (RECYCLE_TAG.equals(myFile.getFileTag())) {
            FileUtil.deleteFile(myFile.getFilePath());
            FileUtil.deleteFile(myFile.getMiniPath());
            myFileRepository.delete(myFile);

        } else {//把文件放入回收站
            myFile.setFileTag(RECYCLE_TAG);
            myFileRepository.save(myFile);
        }
    }

    @Override
    public List<MyFile> getFilesByTag(String userId, String tag) {
        List<MyFile> myFiles = myFileRepository.findMyFilesByUserIdAndFileTagAndCanReadOrderByCreateTimeAsc(userId, tag, true);
        return myFiles;
    }


}
