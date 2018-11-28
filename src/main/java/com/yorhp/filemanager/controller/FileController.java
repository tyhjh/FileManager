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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;

@RestController
@Validated
public class FileController {

    private final static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    FileServiceImpl fileService;

    @PostMapping("/file")
    public Result<String> uploadFile(@Valid MyFile myFile, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.erro(101, bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(fileService.saveFile(myFile));
    }


    @GetMapping("")
    public byte[] getFile(String myFileId, HttpServletRequest request, HttpServletResponse response) {
        File file = new File("/Users/dhht/Downloads/test.png");
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());// 设置文件名
        return FileUtil.getBytes(file);
        //return fileService.getFile(myFileId);
    }

}
