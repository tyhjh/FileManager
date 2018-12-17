package com.yorhp.filemanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/upload")
public class UploadController {

    @RequestMapping("/chooseFile")
    public String upload() {
        return "upload";
    }

}
