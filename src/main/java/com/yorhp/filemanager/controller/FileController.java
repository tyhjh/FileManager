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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
@Validated
public class FileController {

    private final static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    FileServiceImpl fileService;

    @PostMapping("/file")
    public Result<String> uploadFile(@RequestParam("fileTag") String fileTag,
                                     @RequestParam("userId") String userId,
                                     @RequestParam("myFile") MultipartFile file) {
        return ResultUtil.success(fileService.saveFile(fileTag,userId, file));
    }


    @GetMapping("/files")
    public Result<MyFile> getFile(@RequestParam("userId") String userId) {
        return ResultUtil.success(fileService.getFiles(userId));
    }
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