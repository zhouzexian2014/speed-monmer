package com.speed.module.system.controller;


import cn.hutool.json.JSONUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.speed.module.system.dao.TraceLogDao;
import com.speed.module.system.entity.TraceLog;
import com.speed.mutual.common.annotation.IgnoreLog;
import com.speed.mutual.common.dto.PageDTO;
import com.speed.mutual.common.dto.ResponseDTO;
import com.speed.mutual.common.form.SearchPageForm;
import com.speed.mutual.common.utils.ResponseUtil;
import com.speed.mutual.webjpa.controller.BaseController;
import com.speed.mutual.webjpa.dao.BaseRepository;
import com.speed.mutual.webjpa.utils.JpaUtils;
import com.speed.mutual.webjpa.utils.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 日志追踪表 前端控制器
 * </p>
 *
 * @author Joey
 * @since 2022-03-23
 */
@Api(tags = "日志")
@Slf4j
@RestController
@RequestMapping("/sys/log")
public class TraceLogController {
    @Resource
    TraceLogDao traceLogDao;

    public BaseRepository<TraceLog,String> getService() {
        return traceLogDao;
    }

    @IgnoreLog
    @ApiOperationSupport(order = 99905)
    @ApiOperation(value = "日志列表")
    @PostMapping("/page")
    public ResponseDTO<PageDTO<TraceLog,Long>> page(@RequestBody SearchPageForm form){
        try {
            Sort sortOrder = JpaUtils.getSortOrder(form.getSortOrders());
            Pageable pageable = PageRequest.of(form.getPageNo() - 1, form.getPageSize(), sortOrder);
            Specification<TraceLog> specification = JpaUtils.specification(form.getSearchAndItemList());
            Page<TraceLog> page = getService().findAll(specification, pageable);
            return ResponseUtil.ok(PageUtil.getPageVo(page.getTotalPages(),page.getTotalElements(),page.getContent()));
        }catch (Exception e){
            log.error("-查询***列表-分页检索记录-出错,form="+ JSONUtil.toJsonPrettyStr(form),e);
            return ResponseUtil.error(e.getMessage());
        }
    }
}

