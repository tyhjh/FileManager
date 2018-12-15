package com.yorhp.filemanager.service;

import com.yorhp.filemanager.domin.MyFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FileService {

    /**
     * 保存文件到本地和数据库
     * @param myFile
     * @param file
     * @return
     */
    MyFile saveFile(MyFile myFile, MultipartFile file);

    /**
     * 根据用户ID获取文件
     * @param fileId
     * @return
     */
    List<MyFile> getFiles(String fileId);

    /**
     * 删除文件到垃圾桶
     * @param fileId
     */
    void deleteFile(Long fileId);

    /**
     * 根据文件ID和TAG获取文件
     * @param fileId
     * @param tag
     * @return
     */
    List<MyFile> getFilesByTag(String fileId,String tag);


}
