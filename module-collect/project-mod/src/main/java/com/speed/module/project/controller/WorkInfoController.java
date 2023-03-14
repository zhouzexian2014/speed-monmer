package com.speed.module.project.controller;

import com.speed.module.project.dao.WorkInfoDao;
import com.speed.module.project.entity.WorkInfo;
import com.speed.mutual.webjpa.controller.BaseController;
import com.speed.mutual.webjpa.dao.BaseRepository;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "工序管理")
@Slf4j
@RestController
@RequestMapping("/prj/work")
public class WorkInfoController extends BaseController<WorkInfo> {
    @Resource
    WorkInfoDao workInfoDao;

    @Override
    public BaseRepository<WorkInfo, String> getService() {
        return workInfoDao;
    }
}
