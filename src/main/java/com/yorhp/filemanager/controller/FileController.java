package com.yorhp.filemanager.controller;

import com.yorhp.filemanager.domin.MyFile;
import com.yorhp.filemanager.domin.Result;
import com.yorhp.filemanager.service.impl.FileServiceImpl;
import com.yorhp.filemanager.util.FileUtil;
import com.yorhp.filemanager.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@RestController
@Validated
public class FileController {

    private final static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    FileServiceImpl fileService;

    @PostMapping("/file")
    public Result<MyFile> uploadFile(
            @Valid MyFile myFile, BindingResult bindingResult,
            @RequestParam("myFile") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.erro(101, bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(fileService.saveFile(myFile, file));
    }


    @GetMapping("/files")
    public Result<List<MyFile>> getFile(@RequestParam("userId") String userId) {
        return ResultUtil.success(fileService.getFiles(userId));
    }


    @DeleteMapping("/file")
    public Result<String> deleteFile(@RequestParam("myFileId") Long userId) {
        fileService.deleteFile(userId);
        return ResultUtil.success();
    }

    @GetMapping("/tag/files")
    public Result<List<MyFile>> getFilesByTag(@RequestParam("userId") String userId,
                                              @RequestParam("fileTag") String fileTag) {
        return ResultUtil.success(fileService.getFilesByTag(userId, fileTag));
    }

    @GetMapping("/test")
    public Result test() {
        return ResultUtil.success();
    }

}