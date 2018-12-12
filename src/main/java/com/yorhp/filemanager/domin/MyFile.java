package com.yorhp.filemanager.domin;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.File;

@Entity
public class MyFile {

    @Id
    @NotNull(message = "myFileId不能为空")
    private Long myFileId;

    //文件URL地址
    private String fileUrl;

    //文件缩略图
    private String fileMiniUrl;

    @NotNull(message = "userId不能为空")
    private String userId;


    //文件名字
    private String fileName;

    //文件地址
    private String filePath;

    private String localPath;

    //文件类型
    private String fileType;

    //文件大小
    private Double fileSize;

    //缩略图文件大小
    private Double miniFileSize;

    //文件标签
    @NotNull(message = "fileTag不能为空")
    private String fileTag;

    //访问次数
    private Integer visitTime = 0;

    //最后访问时间
    private Long lastVisitTime;


    //是否禁止访问
    private Boolean canRead = true;

    //创建时间
    private Long createTime;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @JsonIgnore
    public String getFilePath() {
        return filePath;
    }

    @JsonIgnore
    public String getMiniPath() {
        return App.appDirMini + "mini_" + fileName;
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

    @JsonIgnore
    public Integer getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Integer visitTime) {
        this.visitTime = visitTime;
    }

    @JsonIgnore
    public Boolean getCanRead() {
        return canRead;
    }

    public void setCanRead(Boolean canRead) {
        this.canRead = canRead;
    }

    @JsonIgnore
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

    @JsonIgnore
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }


    public Long getMyFileId() {
        return myFileId;
    }

    public void setMyFileId(Long myFileId) {
        this.myFileId = myFileId;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getFileMiniUrl() {
        return fileMiniUrl;
    }

    public void setFileMiniUrl(String fileMiniUrl) {
        this.fileMiniUrl = fileMiniUrl;
    }

    public Double getMiniFileSize() {
        return miniFileSize;
    }

    public void setMiniFileSize(Double miniFileSize) {
        this.miniFileSize = miniFileSize;
    }


    @Override
    public String toString() {
        return "MyFile{" +
                "myFileId=" + myFileId +
                ", fileUrl='" + fileUrl + '\'' +
                ", fileMiniUrl='" + fileMiniUrl + '\'' +
                ", userId='" + userId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", localPath='" + localPath + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileSize=" + fileSize +
                ", miniFileSize=" + miniFileSize +
                ", fileTag='" + fileTag + '\'' +
                ", visitTime=" + visitTime +
                ", lastVisitTime=" + lastVisitTime +
                ", canRead=" + canRead +
                ", createTime=" + createTime +
                '}';
    }
}
