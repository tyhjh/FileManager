package com.yorhp.filemanager.handle;

import com.yorhp.filemanager.domin.Result;
import com.yorhp.filemanager.exception.MlException;
import com.yorhp.filemanager.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof MlException) {
            MlException mlException = (MlException) e;
            return ResultUtil.erro(mlException.getCode(), mlException.getMessage());
        } else if (e instanceof ServletRequestBindingException) {
            return ResultUtil.erro(101, e.getMessage());
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            return ResultUtil.erro(101, e.getMessage());
        } else {
            logger.info("【系统异常】", e);
            return ResultUtil.erro(100, "未知错误");
        }
    }


    //参数验证的异常捕获
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleApiConstraintViolationException(ConstraintViolationException ex) {
        String message = "";
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            message += violation.getMessage() + ", ";
        }
        return ResultUtil.erro(101, message);
    }

}
