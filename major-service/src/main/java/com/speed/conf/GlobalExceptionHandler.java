package com.speed.conf;


import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.speed.mutual.common.dto.ResponseDTO;
import com.speed.mutual.common.utils.ResponseUtil;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 校验异常
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ResponseDTO res = ResponseUtil.error("校验不通过");
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        res.setData(allErrors);
        return res;
    }
    /**
     * 未登录的异常
     */
    @ResponseBody
    //@ExceptionHandler(value = NotLoginException.class)
    public ResponseDTO handleNotLoginException(NotLoginException e) {
        return ResponseUtil.unauthorized("没有token");
    }
    /**
     * 没有角色的异常
     */
    @ResponseBody
    @ExceptionHandler(value = NotRoleException.class)
    public ResponseDTO handleNotRoleException(NotRoleException e) {
        return ResponseUtil.unauthorized("没有角色");
    }
    /**
     * 没有权限的异常
     */
    @ResponseBody
    @ExceptionHandler(value = NotPermissionException.class)
    public ResponseDTO handleNotPermissionException(NotPermissionException e) {
        return ResponseUtil.unauthorized("没有权限");
    }

}
