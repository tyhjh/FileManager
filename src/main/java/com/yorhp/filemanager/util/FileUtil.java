package com.yorhp.filemanager.util;


import com.yorhp.filemanager.enums.ResultEnum;
import com.yorhp.filemanager.exception.MlException;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
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

    public static File saveFile(MultipartFile multipartFile, String dir) {
        String time = String.valueOf(System.currentTimeMillis());
        String path = null;
        File newFile = null;
        try {
            path = dir + multipartFile.getOriginalFilename();
            path = path.substring(0, path.lastIndexOf(".")) + "#" + (time.substring(time.length() - 3, time.length())) + path.substring(path.lastIndexOf("."), path.length());
            newFile = new File(path);
            multipartFile.transferTo(newFile);
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
     *
     * @param file 文件
     * @return
     */
    public static byte[] getBytes(File file) {
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


    public static File downLoadFile(MultipartFile file, String path) throws IOException {
        if (file.isEmpty())
            throw new MlException(ResultEnum.UPLOADFILE_NOTEXIT);
        File uploadFile = new File(path);
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(uploadFile));
        out.write(file.getBytes());
        out.flush();
        out.close();
        return uploadFile;
    }

    public static String getFileType(String fileName) {
        String type = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
        if (!type.isEmpty()) {
            type.toUpperCase();
        }
        return type;
    }

    public static boolean isPic(String fileName) {
        if (fileName.equals("PNG") || fileName.equals("JPG") || fileName.equals("JPEG")) {
            return true;
        }
        return false;
    }


    /**
     * 压缩文件
     *
     * @param originFile     原文件路径
     * @param savePath       保存文件路径
     * @param targetFileSize 大概压缩后大小
     * @return
     */
    public static File compressPic(File originFile, String savePath, double targetFileSize, int targetFileWidth) {
        float scale = 1f;
        float quality = 1f;
        try {
            double fileSize = getFileSize(originFile);
            BufferedImage sourceImg = ImageIO.read(new FileInputStream(originFile));
            scale = targetFileWidth / sourceImg.getHeight();
            if (scale > 1) {
                scale = 1;
            }
            if (fileSize > 30 * targetFileSize / scale) {
                quality = 0.3f;
            } else if (fileSize > 20 * targetFileSize / scale) {
                quality = 0.4f;
            } else if (fileSize > 10 * targetFileSize / scale) {
                quality = 0.5f;
            } else if (fileSize > 5 * targetFileSize / scale) {
                quality = 0.6f;
            } else {
                quality = 0.7f;
            }
            Thumbnails.of(originFile.getPath())
                    .scale(scale)
                    .outputQuality(quality)
                    .toFile(savePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File(savePath);
    }


}
