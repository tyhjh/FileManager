package com.yorhp.filemanager.util;


import com.yorhp.filemanager.domin.Result;

public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        result.setData(object);
        return result;
    }


    public static Result success() {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        return result;
    }

    public static Result erro(int code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
