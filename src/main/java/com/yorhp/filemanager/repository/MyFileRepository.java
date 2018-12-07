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
    MyFile findMyFileByMyFileIdAndCanRead(String myfileId, Boolean canRead);

    /**
     * 根据文件名匹配文件
     *
     * @param fileName
     * @param canRead
     * @return
     */
    List<MyFile> findMyFilesByFileNameAndCanRead(String fileName, Boolean canRead);

    /**
     * 根据用户查找
     *
     * @param userId
     * @param canRead
     * @return
     */
    List<MyFile> findMyFilesByUserIdAndCanReadOrderByCreateTimeAsc(String userId, Boolean canRead);

    /**
     *
     * @param myFileId
     * @return
     */
    MyFile findFirstByMyFileId(Long myFileId);

    /**
     * 根据用户和标签查找图片
     *
     * @param userId
     * @param tag
     * @param canRead
     * @return
     */
    List<MyFile> findMyFilesByUserIdAndFileTagAndCanReadOrderByCreateTimeAsc(String userId, String tag, Boolean canRead);


    /**
     * 获取所有图片
     * @param userId
     * @param tag
     * @param canRead
     * @return
     */
    List<MyFile> findMyFilesByUserIdAndFileTagNotAndCanReadOrderByCreateTimeAsc(String userId, String tag, Boolean canRead);

}
