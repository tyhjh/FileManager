package com.yorhp.filemanager;

import com.yorhp.filemanager.domin.App;
import com.yorhp.filemanager.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class FilemanagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilemanagerApplication.class, args);
        FileUtil.createDir(App.appDir);
        FileUtil.createDir(App.appDirMini);
    }
}
