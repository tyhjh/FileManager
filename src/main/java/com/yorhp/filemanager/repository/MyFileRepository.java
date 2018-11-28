package com.yorhp.filemanager.repository;

import com.yorhp.filemanager.domin.MyFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyFileRepository extends JpaRepository<MyFile, Integer> {

    /**
     * 根据ID找文件
     *
     * @param myfileId
     * @param canRead  图片是否可用
     * @return
     */
    public MyFile findMyFileByMyFileIdAndCanRead(String myfileId, Boolean canRead);

    /**
     * 根据文件名匹配文件
     *
     * @param fileName
     * @param canRead
     * @return
     */
    public List<MyFile> findMyFilesByFileNameAndCanRead(String fileName, Boolean canRead);

    /**
     * 根据用户查找ID
     *
     * @param userId
     * @param canRead
     * @return
     */
    public List<MyFile> findMyFilesByUserIdAndCanReadOrderByCreateTime(String userId, Boolean canRead);

}
