package com.yorhp.filemanager.domin;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.File;

@Entity
public class MyFile {

    @Id
    @GeneratedValue
    private String myFileId;

    @Transient
    File file;

    private String userId;

    //文件名字
    private String fileName;

    //文件地址
    private String filePath;

    //文件类型
    private String fileType;

    //文件大小
    private Double fileSize;

    //文件标签
    @NotNull(message = "文件标签不能为空")
    private String fileTag;

    //访问次数
    private Integer visitTime = 0;

    //最后访问时间
    private Long lastVisitTime;

    //是否禁止访问
    private Boolean canRead = true;

    //创建时间
    private Long createTime;


    public String getMyFileId() {
        return myFileId;
    }

    public void setMyFileId(String myFileId) {
        this.myFileId = myFileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Double getFileSize() {
        return fileSize;
    }

    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileTag() {
        return fileTag;
    }

    public void setFileTag(String fileTag) {
        this.fileTag = fileTag;
    }

    public Integer getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Integer visitTime) {
        this.visitTime = visitTime;
    }

    public Boolean getCanRead() {
        return canRead;
    }

    public void setCanRead(Boolean canRead) {
        this.canRead = canRead;
    }

    public Long getLastVisitTime() {
        return lastVisitTime;
    }

    public void setLastVisitTime(Long lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
