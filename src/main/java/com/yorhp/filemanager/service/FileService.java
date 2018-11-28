package com.yorhp.filemanager.service;

import com.yorhp.filemanager.domin.MyFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileService {

    //保存文件到本地和数据库
    String saveFile(String tag, MultipartFile file);

    //根据文件ID获取文件
    File getFile(String fileId);
}
