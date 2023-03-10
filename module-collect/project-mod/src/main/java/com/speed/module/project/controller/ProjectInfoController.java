package com.speed.module.project.controller;

import com.speed.module.project.dao.ProjectInfoDao;
import com.speed.module.project.entity.ProjectInfo;
import com.speed.mutual.webjpa.controller.BaseController;
import com.speed.mutual.webjpa.dao.BaseRepository;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "项目")
@Slf4j
@RestController
@RequestMapping("/prj/project")
public class ProjectInfoController extends BaseController<ProjectInfo> {
    @Resource
    ProjectInfoDao projectInfoDao;

    @Override
    public BaseRepository<ProjectInfo, String> getService() {
        return projectInfoDao;
    }
}
