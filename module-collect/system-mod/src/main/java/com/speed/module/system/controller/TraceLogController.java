package com.speed.module.system.controller;


import com.speed.module.system.dao.TraceLogDao;
import com.speed.module.system.entity.TraceLog;
import com.speed.mutual.webjpa.controller.BaseController;
import com.speed.mutual.webjpa.dao.BaseRepository;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
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
public class TraceLogController extends BaseController<TraceLog> {
    @Resource
    TraceLogDao traceLogDao;

    @Override
    public BaseRepository<TraceLog,String> getService() {
        return traceLogDao;
    }
}

