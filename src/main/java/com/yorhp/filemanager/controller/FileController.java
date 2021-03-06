package com.yorhp.filemanager.controller;

import com.yorhp.filemanager.domin.MyFile;
import com.yorhp.filemanager.domin.Result;
import com.yorhp.filemanager.service.impl.FileServiceImpl;
import com.yorhp.filemanager.util.FileUtil;
import com.yorhp.filemanager.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
    public Result<MyFile> uploadPic(
            @Valid MyFile myFile, BindingResult bindingResult,
            @RequestParam("myFile") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.erro(101, bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(fileService.savePic(myFile, file));
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

    @PostMapping("/file/receive")
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file) {
        return ResultUtil.success(fileService.saveFile(file));
    }

    /*File file = fileService.getFile(myFileId);
        boolean isOnLine = true;
        try {
            BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
            byte[] buf = new byte[1024];
            int len = 0;
            response.reset();
            if (isOnLine) { // 在线打开方式
                URL u = new URL("file:///" + file.getAbsolutePath());
                response.setContentType(u.openConnection().getContentType());
                response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
            } else { // 纯下载方式
                response.setContentType("application/x-msdownload");
                response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            }
            OutputStream out = response.getOutputStream();
            while ((len = br.read(buf)) > 0)
                out.write(buf, 0, len);
            br.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}