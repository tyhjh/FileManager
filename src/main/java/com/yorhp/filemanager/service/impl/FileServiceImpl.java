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

    @Autowired
    MyFileRepository myFileRepository;

    @Override
    @Transactional
    public String saveFile(String tag, String userId, MultipartFile file) {
        try {
            MyFile myFile = new MyFile();
            myFile.setFileTag(tag);
            File newFile = FileUtil.saveFile(file, App.appDir);
            String fileName = newFile.getName();
            myFile.setCreateTime(System.currentTimeMillis());
            myFile.setFileName(newFile.getName());
            myFile.setFilePath(newFile.getPath());
            myFile.setUserId(userId);
            myFile.setFileSize(FileUtil.getFileSize(newFile));
            String type = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
            if (!type.isEmpty()) {
                type.toUpperCase();
            }
            myFile.setFileType(type);
            myFile.setFileUrl(App.domainName + URLEncoder.encode(myFile.getFileName(), "UTF-8"));
            myFileRepository.save(myFile);
            return myFile.getFileUrl();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new MlException(ResultEnum.LACK_VALUE);
    }

    @Override
    @Transactional
    public List<MyFile> getFiles(String userId) {
        List<MyFile> myFile = myFileRepository.findMyFilesByUserIdAndCanReadOrderByCreateTime(userId, true);
        return myFile;
    }
}
