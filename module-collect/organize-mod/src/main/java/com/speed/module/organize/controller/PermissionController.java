package com.speed.module.organize.controller;

import com.speed.module.organize.dao.PermissionDao;
import com.speed.module.organize.entity.Permission;
import com.speed.mutual.webjpa.controller.BaseController;
import com.speed.mutual.webjpa.dao.BaseRepository;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "权限管理")
@Slf4j
@RestController
@RequestMapping("/org/pes")
public class PermissionController extends BaseController<Permission> {
    @Resource
    PermissionDao permissionDao;
    @Override
    public BaseRepository<Permission, String> getService() {
        return permissionDao;
    }
}
