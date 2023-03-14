package com.speed.module.organize.controller;

import com.speed.module.organize.dao.RoleDao;
import com.speed.module.organize.entity.Role;
import com.speed.mutual.webjpa.controller.BaseController;
import com.speed.mutual.webjpa.dao.BaseRepository;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "角色管理")
@Slf4j
@RestController
@RequestMapping("/org/role")
public class RoleController extends BaseController<Role> {
    @Resource
    RoleDao roleDao;
    @Override
    public BaseRepository<Role, String> getService() {
        return roleDao;
    }
}
