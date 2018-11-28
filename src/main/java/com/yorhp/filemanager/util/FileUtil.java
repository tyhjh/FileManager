package com.yorhp.filemanager.util;


import com.yorhp.filemanager.enums.ResultEnum;
import com.yorhp.filemanager.exception.MlException;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
        String path = null;
        File newFile=null;
        try {
            path = dir + (time.substring(time.length() - 3, time.length())) + URLEncoder.encode(file.getName(), "UTF-8");
            newFile = new File(path);
            copyFileUsingFileStreams(file, newFile);
            newFile.createNewFile();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFile;
    }


    private static void copyFileUsingFileStreams(File source, File dest)
            throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
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


    public static File downLoadFile(MultipartFile file) throws IOException {
        if (file.isEmpty())
            throw new MlException(ResultEnum.UPLOADFILE_NOTEXIT);
        File uploadFile = new File(file.getOriginalFilename());
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(uploadFile));
        out.write(file.getBytes());
        out.flush();
        out.close();
        return uploadFile;
    }

}
