package com.speed.controller;

import com.speed.module.organize.dao.PermissionDao;
import com.speed.module.organize.entity.Permission;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class TestDemo {
    @Resource
    PermissionDao permissionDao;

    @Test
    public void test(){
        List<Permission> permissions = permissionDao.queryListByDeptId("");
        System.out.println(permissions.size());

    }
}
