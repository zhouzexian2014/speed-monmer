package com.speed.module.project.controller;

import com.speed.module.project.dao.TaskInfoDao;
import com.speed.module.project.entity.TaskInfo;
import com.speed.mutual.webjpa.controller.BaseController;
import com.speed.mutual.webjpa.dao.BaseRepository;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "任务")
@Slf4j
@RestController
@RequestMapping("/prj/task")
public class TaskInfoController extends BaseController<TaskInfo> {
    @Resource
    TaskInfoDao taskInfoDao;
    @Override
    public BaseRepository<TaskInfo, String> getService() {
        return taskInfoDao;
    }
}
