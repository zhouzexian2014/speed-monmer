package com.speed.module.system.controller;

import cn.hutool.cache.file.LFUFileCache;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.speed.mutual.common.dto.ResponseDTO;
import com.speed.mutual.common.utils.FieldUtils;
import com.speed.mutual.common.utils.ResponseUtil;
import com.speed.module.system.dao.FileInfoDao;
import com.speed.module.system.entity.FileInfo;
import com.speed.module.system.form.UploadFileForm;
import com.speed.module.system.service.FileHandle;
import com.speed.mutual.common.constant.BaseConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api(tags = "文件")
@Slf4j
@RestController
@RequestMapping("/sys/file")
public class FileInfoController {
    @Value("${monomer.file-path}")
    String baseFilePath;
    @Resource
    FileHandle fileHandle;
    @Resource
    FileInfoDao fileInfoDao;
    LFUFileCache cache = new LFUFileCache(1024*1024*500, 1024*1024*10, 1000*60*60*24);

    @ApiOperation(value = "删除文件")
    @DeleteMapping("")
    public ResponseDTO delete(String ids){
        if(StrUtil.isBlank(ids)){
            return ResponseUtil.error("请先选择数据");
        }
        String[] idArr = ids.split(",");
        List<String> deleteIdList = new ArrayList<>(idArr.length);
        for(String id : idArr){
            Optional<FileInfo> fileInfo = fileInfoDao.findById(id);
            if(fileInfo.isPresent()){
                JSONObject param = JSONUtil.parseObj(fileInfo);
                Boolean delete = fileHandle.delete(param);
                if(delete){
                    fileInfoDao.deleteById(id);
                    deleteIdList.add(id);

                }
            }
        }
        return ResponseUtil.ok(deleteIdList);
    }

    @ApiOperationSupport(order = 99904)
    @ApiOperation(value = "通用查询单例" )
    @GetMapping("/{id}")
    @ApiImplicitParam(name="id",value="id",dataType = "string", paramType = "path",required = true)
    public ResponseDTO<FileInfo> get(@PathVariable String id){
        try {
            FileInfo fileInfo = fileInfoDao.getById(id);
            return ResponseUtil.ok(fileInfo);
        }catch (Exception e){
            log.error("通用查询单例-出错,id="+id,e);
            return ResponseUtil.error();
        }
    }



    @ApiOperation(value = "上传")
    @PostMapping("/upload")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "files",value = "文件",dataType = "__file",paramType = "form",required = true)
    })
    public ResponseDTO uploadFile(UploadFileForm form, HttpServletRequest request) throws Exception{
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files");
        if(files==null||files.size()==0||files.get(0).isEmpty()){
            return ResponseUtil.error("文件不能为空");
        }
        List<String> fileIdList = new ArrayList(files.size());
        for(MultipartFile file : files){
            String fileName = file.getOriginalFilename();
            String extension=fileName.substring(fileName.lastIndexOf(BaseConstant.SPITS_STR4));
            FileInfo info = new FileInfo();
            info.setContentType(file.getContentType());
            info.setExtension(extension);
            info.setLength(file.getSize());
            info.setFileName(fileName);
            info.setRelationKey(form.getRelationKey());
            info.setRelationType(form.getRelationType());
            JSONObject param = new JSONObject();
            param.putOnce("fileName", fileName);
            param = fileHandle.upload(file.getInputStream(), param);
            info.setFilePath(param.getStr("filePath"));
            fileInfoDao.save(info);
            fileIdList.add(info.getId());
        }
        return ResponseUtil.ok(fileIdList);
    }
    @ApiOperation(value = "下载")
    @GetMapping("/download/{fileId}")
    public void downloadFile(@PathVariable("fileId") String fileId,HttpServletResponse response) throws Exception {
        FileInfo fileInfo = fileInfoDao.getById(fileId);
        if(fileInfo==null){
            throw new RuntimeException("文件不存在");
        }
        //校验文件权限
//        ResponseDTO res = fileInfoService.checkFileAuthorization(fileInfo);
//        if(!res.getSuccess()){
//            throw new RuntimeException(res.getMsg());
//        }
        //输出文件
        byte[] fileBytes = cache.getFileBytes(baseFilePath + fileInfo.getFilePath());
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileInfo.getFileName().getBytes(),"iso-8859-1"));
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(fileBytes);
        outputStream.flush();
    }

    @ApiOperationSupport(order = 99906)
    @ApiOperation(value = "查询类字段")
    @GetMapping("/fieldList")
    public ResponseDTO fieldList() {
        try {
            return ResponseUtil.ok(FieldUtils.getColumns(FileInfo.class));
        }catch (Exception e){
            log.error("通用查询类字段-出错,class="+FileInfo.class.getCanonicalName(),e);
            return ResponseUtil.error();
        }
    }
}
