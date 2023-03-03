package com.speed.module.system.service;

import cn.hutool.json.JSONObject;

import java.io.InputStream;

/**
 * 文件处理接口
 */
public interface FileHandle {
    /**
     * 文件上传
     */
    JSONObject upload(InputStream inputStream,JSONObject param) throws Exception;
    /**
     * 文件下载
     */
    JSONObject download(JSONObject param) throws Exception;

    /**
     * 文件删除
     */
    Boolean delete(JSONObject param);
}
