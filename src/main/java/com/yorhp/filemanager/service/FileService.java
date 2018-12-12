package com.yorhp.filemanager.service;

import com.yorhp.filemanager.domin.MyFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FileService {

    //保存文件到本地和数据库
    MyFile saveFile(MyFile myFile, MultipartFile file);

    //根据用户ID获取文件
    List<MyFile> getFiles(String fileId);

    //删除文件到垃圾桶
    void deleteFile(Long fileId);

    //根据文件ID和TAG获取文件
    List<MyFile> getFilesByTag(String fileId,String tag);


}
