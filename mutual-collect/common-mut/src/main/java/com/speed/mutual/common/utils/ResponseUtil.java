package com.speed.mutual.common.utils;

import cn.hutool.json.JSONUtil;
import com.speed.mutual.common.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 响应工具类
 */
public class ResponseUtil {
    private static final Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

    public static void main(String[] args) {
        String contract="GFPSN-20210923-008";
        String[] arr = contract.split("-");
        System.out.println(arr[2]);
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static ResponseDTO ok(Object data){
        ResponseDTO dto = new ResponseDTO();
        dto.setSuccess(true);
        dto.setCode(200);
        dto.setData(data);
        return dto;
    }
    public static ResponseDTO ok(){
        return ok(null);
    }
    /**
     * 失败
     * @param msg
     * @return
     */
    public static ResponseDTO error(String msg){
        ResponseDTO dto = new ResponseDTO();
        dto.setCode(500);
        dto.setSuccess(false);
        dto.setMsg(msg);
        return dto;
    }
    public static ResponseDTO error(String msg,Object data){
        ResponseDTO dto = new ResponseDTO();
        dto.setCode(500);
        dto.setSuccess(false);
        dto.setMsg(msg);
        dto.setData(data);
        return dto;
    }
    public static ResponseDTO error(){
        return error(null);
    }
    /**
     * 没有权限
     * @param msg
     * @return
     */
    public static ResponseDTO unauthorized(String msg) {
        ResponseDTO dto = new ResponseDTO();
        dto.setCode(403);
        dto.setSuccess(false);
        dto.setMsg(msg);
        return dto;
    }
    /**
     * 返回响应
     * @param dto
     */
    public static  void  handle(Object dto){
        handle(JSONUtil.toJsonStr(dto));
    }
    /**
     * 返回响应
     * @param res
     */
    public static  void  handle(String res){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(res);
        }catch (Exception e){
            logger.error("响应返回出错",e);
        }finally {
            if(out!=null){
                out.close();
            }
        }
    }


}
