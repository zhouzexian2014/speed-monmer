package com.speed.mutual.webjpa.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.speed.mutual.webjpa.bean.IdEntity;
import com.speed.mutual.common.constant.BaseConstant;
import com.speed.mutual.common.dto.PageDTO;
import com.speed.mutual.common.dto.ResponseDTO;
import com.speed.mutual.common.form.SearchPageForm;
import com.speed.mutual.common.utils.FieldUtils;
import com.speed.mutual.common.utils.ResponseUtil;
import com.speed.mutual.webjpa.dao.BaseRepository;
import com.speed.mutual.webjpa.listener.JpaExcelListener;
import com.speed.mutual.webjpa.utils.JpaUtils;
import com.speed.mutual.webjpa.utils.PageUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 基本操作封装
 * @author joey
 */
@Slf4j
public abstract class BaseController<T extends IdEntity> {
    /**
     * 获取操作Service
     * @return
     */
    public abstract BaseRepository<T,String> getService();

    @ApiOperationSupport(order = 99901)
    @ApiOperation(value = "通用新增" )
    @PostMapping("")
    public ResponseDTO<T> insert(@RequestBody T t){
        try {
            t.setId(null);
            T save = getService().save(t);
            if(save!=null){
                return ResponseUtil.ok(save);
            }
            return ResponseUtil.error("新增失败");
        }catch (Exception e){
            log.error("--新增-***-出错",e);
            log.error( "class="+t.getClass().getCanonicalName());
            log.error( JSONUtil.toJsonPrettyStr(t));
            return ResponseUtil.error(e.getMessage());
        }
    }

    @ApiOperationSupport(order = 99902)
    @ApiOperation(value = "通用删除")
    @DeleteMapping("")
    @ApiImplicitParam(name="ids",value="id集合，以英文逗号分隔",dataType = "string", paramType = "query",required = true)
    public ResponseDTO delete(String ids){
        try {
            if(StrUtil.isNotBlank(ids)){
                String[] arrId = ids.split(BaseConstant.SPITS_STR1);
                List<String> list = new ArrayList<>(arrId.length);
                Collections.addAll(list,arrId);
                getService().deleteAllByIdInBatch(list);
                return ResponseUtil.ok();
            }
            return ResponseUtil.error("删除失败");
        }catch (Exception e){
            log.error("-删除-***-出错,ids="+ids,e);
            return ResponseUtil.error(e.getMessage());
        }
    }

    @ApiOperationSupport(order = 99903)
    @ApiOperation(value = "通用更新" )
    @PutMapping("")
    public ResponseDTO<T> update(@RequestBody T t){
        try {
            Optional<T> one = getService().findById(t.getId());
            if(one.isPresent()){
                T update = getService().save(t);
                if(update!=null){
                    return ResponseUtil.ok(update);
                }
            }
            return ResponseUtil.error("更新失败");
        }catch (Exception e){
            log.error("--更新-***-出错",e);
            log.error( "class="+t.getClass().getCanonicalName());
            log.error( JSONUtil.toJsonPrettyStr(t));
            return ResponseUtil.error(e.getMessage());
        }
    }

    @ApiOperationSupport(order = 99904)
    @ApiOperation(value = "通用查询单例" )
    @GetMapping("")
    @ApiImplicitParam(name="id",value="主键",dataType = "string", paramType = "query",required = true)
    public ResponseDTO<T> get(String id){
        try {
            Optional<T> one = getService().findById(id);
            if(one.isPresent()){
                return ResponseUtil.ok(getService().getById(id));
            }
            return ResponseUtil.error("查询不到数据");
        }catch (Exception e){
            log.error("-查询***-根据id-出错,id="+id,e);
            return ResponseUtil.error(e.getMessage());
        }
    }



    @ApiOperationSupport(order = 99905)
    @ApiOperation(value = "通用列表")
    @PostMapping("/page")
    public ResponseDTO<PageDTO<T,Long>> page(@RequestBody SearchPageForm form){
        try {
            Sort sortOrder = JpaUtils.getSortOrder(form.getSortOrders());
            Pageable pageable = PageRequest.of(form.getPageNo() - 1, form.getPageSize(), sortOrder);
            Specification<T> specification = JpaUtils.specification(form.getSearchAndItemList());
            Page<T> page = getService().findAll(specification, pageable);
            return ResponseUtil.ok(PageUtil.getPageVo(page.getTotalPages(),page.getTotalElements(),page.getContent()));
        }catch (Exception e){
            log.error("-查询***列表-分页检索记录-出错,form="+ JSONUtil.toJsonPrettyStr(form),e);
            return ResponseUtil.error(e.getMessage());
        }
    }

    @ApiOperationSupport(order = 99906)
    @ApiOperation(value = "元数据查询")
    @GetMapping("/metadata")
    public ResponseDTO metadata(@ApiIgnore T t) {
        try {
            ResponseDTO res = ResponseUtil.ok();
            Map<String,Object> map = new HashMap<>();
            ApiModel apiModel = t.getClass().getAnnotation(ApiModel.class);
            if(apiModel!=null){
                map.put("modelName",apiModel.value());
            }
            map.put("className",t.getClass().getSimpleName());
            map.put("columns", FieldUtils.getColumns(t.getClass()));
            res.setData(map);
            return res;
        }catch (Exception e){
            log.error("通用查询类字段-出错",e);
            log.error( "class="+t.getClass().getCanonicalName());
            return ResponseUtil.error(e.getMessage());
        }
    }

    @ApiOperationSupport(order = 99905)
    @ApiOperation(value = "Excel导出")
    @GetMapping("/exportExcel")
    public void exportExcel(SearchPageForm form){
        try {
            Sort sortOrder = JpaUtils.getSortOrder(form.getSortOrders());
            Pageable pageable = PageRequest.of(form.getPageNo() - 1, form.getPageSize(), sortOrder);
            Specification<T> specification = JpaUtils.specification(form.getSearchAndItemList());
            Page<T> page = getService().findAll(specification, pageable);
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            ServletOutputStream outputStream = response.getOutputStream();
            Class<? extends IdEntity> aClass = page.getContent().get(0).getClass();
            ApiModel annotation = aClass.getAnnotation(ApiModel.class);
            String timeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern(BaseConstant.DATE_FORMAT5));
            String fileName = annotation.description()+timeStr;
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes(),"iso-8859-1")+".xlsx");
            EasyExcel.write(outputStream,aClass).sheet(fileName).doWrite(page.getContent());
        }catch (Exception e){
            log.error("-excel导出-出错,form="+ JSONUtil.toJsonPrettyStr(form),e);
            ResponseUtil.handle("-excel导出-出错,form="+ JSONUtil.toJsonPrettyStr(form)+",msg="+e.getMessage());
        }
    }

    @ApiOperationSupport(order = 99905)
    @ApiOperation(value = "Excel导入")
    @PostMapping("/excelImport")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file",value = "上传excel",dataType = "__file",paramType = "form",required = true)
    })
    public ResponseDTO excelImport(@ApiIgnore T t, HttpServletRequest request){
        try {
            MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
            if(file==null||file.isEmpty()){
                return ResponseUtil.error("文件不能为空");
            }
            if(!file.getOriginalFilename().endsWith(".xlsx")){
                return ResponseUtil.error("文件格式不是xlsx");
            }
            EasyExcel.read(file.getInputStream(),t.getClass(), new JpaExcelListener<T>(getService())).sheet().doRead();
            return ResponseUtil.ok();
        }catch (Exception e){
            log.error("-excel导入-出错,form=",e);
            return ResponseUtil.error(e.getMessage());
        }
    }


}
