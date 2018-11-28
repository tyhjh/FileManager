package com.yorhp.filemanager.exception;


import com.yorhp.filemanager.enums.ResultEnum;

public class MlException extends RuntimeException {
    private Integer code;

    public MlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
