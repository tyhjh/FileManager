package com.yorhp.filemanager.enums;

/**
 * @author Tyhj
 */

public enum ResultEnum {

    UNKNOW_ERRO(-1, "未知错误"),
    SUCCESS(200, "成功"),
    LACK_VALUE(101, "参数出错"),
    VISIT_TOO_OFTEN(110, "访问太频繁"),
    UPLOADFILE_NOTEXIT(125, "上传文件为空"),;
    private Integer code;
    private String msg;


    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
