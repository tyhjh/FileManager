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

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class FileServiceImpl implements FileService {

    private final static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    MyFileRepository myFileRepository;

    @Override
    public String saveFile(MyFile myFile) {
        try {
            File newFile = FileUtil.saveFile(myFile.getFile(), App.appDir);
            String fileName = newFile.getName();
            myFile.setCreateTime(System.currentTimeMillis());
            myFile.setFileName(newFile.getName());
            myFile.setFilePath(newFile.getPath());
            myFile.setFileSize(FileUtil.getFileSize(newFile));
            myFile.setFileType(fileName.substring(fileName.lastIndexOf('.'), fileName.length()));
            String myFileId = URLEncoder.encode(fileName, "UTF-8");
            myFile.setMyFileId(myFileId);
            myFileRepository.save(myFile);
            return App.domainName + myFileId;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        throw new MlException(ResultEnum.LACK_VALUE);
    }

    @Override
    public File getFile(String fileId) {
        MyFile myFile = myFileRepository.findMyFileByMyFileIdAndCanRead(fileId, true);
        File file = new File(myFile.getFilePath());
        myFile.setLastVisitTime(System.currentTimeMillis());
        myFile.setVisitTime(myFile.getVisitTime() + 1);
        myFileRepository.save(myFile);
        return file;
    }
}
