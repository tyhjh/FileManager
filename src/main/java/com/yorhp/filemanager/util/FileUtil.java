package com.yorhp.filemanager.util;


import org.springframework.util.StringUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;

public class FileUtil {

    /**
     * 新建文件夹
     *
     * @param path
     */
    public static void createDir(String path) {
        if (StringUtils.isEmpty(path)) {
            return;
        }
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 获取文件名
     *
     * @param fileName
     */
    public static String getFileName(String dir, String fileName) {

        return null;
    }


    public static File saveFile(File file, String dir) {
        String time = String.valueOf(System.currentTimeMillis());
        String path = dir + (time.substring(time.length() - 4, time.length())) + file.getName();
        File newFile = new File(path);
        try {
            copyFile(file, newFile);
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFile;
    }


    public static void copyFile(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputChannel != null)
                outputChannel.close();
            inputChannel.close();
        }
    }

    public static double getFileSize(File file) {
        FileChannel fc = null;
        if (file.exists() && file.isFile()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                fc = fis.getChannel();
                return (double) (fc.size()) / (1024 * 1024);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }


    /**
     * 将文件转为byte[]
     * @param file 文件
     * @return
     */
    public static byte[] getBytes(File file){
        ByteArrayOutputStream out = null;
        try {
            FileInputStream in = new FileInputStream(file);
            out = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int i = 0;
            while ((i = in.read(b)) != -1) {

                out.write(b, 0, b.length);
            }
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] s = out.toByteArray();
        return s;

    }

}
