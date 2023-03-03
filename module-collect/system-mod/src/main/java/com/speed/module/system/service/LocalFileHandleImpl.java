package com.speed.module.system.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import com.speed.mutual.common.constant.BaseConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDate;

/**
 * 文件处理-本地磁盘操作
 */
@Service
@Slf4j
public class LocalFileHandleImpl implements FileHandle{
    @Value("${monomer.file-path}")
    String baseFilePath;

    /**
     * param.fileName 文件名称  必传
     */
    @Override
    public JSONObject upload(InputStream inputStream, JSONObject param) throws Exception{
        String fileName = param.getStr("fileName");
        String filePath = buildFilePath(fileName);
        String fileAllPath = baseFilePath + filePath;
        File dest = new File(fileAllPath);
        if (!dest.exists()) {
            dest.getParentFile().mkdirs();
            dest.createNewFile();
        }
        FileOutputStream downloadFile = new FileOutputStream(dest);
        int index;
        byte[] bytes = new byte[1024];
        while ((index = inputStream.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        inputStream.close();
        param.putOnce("filePath", filePath);
        return param;
    }

    //构建文件路径
    private String buildFilePath(String fileName) {
        LocalDate now = LocalDate.now();
        StringBuilder sb = new StringBuilder();
        sb.append(File.separator);
        sb.append(now.getYear()+ BaseConstant.SPITS_STR3+now.getMonthValue()+ BaseConstant.SPITS_STR3+now.getDayOfMonth());
        sb.append(File.separator);
        sb.append(IdUtil.objectId());
        sb.append(File.separator);
        sb.append(fileName);
        return sb.toString();
    }

    //文件下载
    @Override
    public JSONObject download(JSONObject param) {

        return null;
    }
    //文件删除
    @Override
    public Boolean delete(JSONObject param){
        String fileAllPath = baseFilePath + param.getStr("filePath");
        File file=new File(fileAllPath);
        if(file.exists()){
            file.delete();
        }
        return true;
    }
}
