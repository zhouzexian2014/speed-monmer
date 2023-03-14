package com.speed.module.organize.conf;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.util.StrUtil;
import com.speed.module.organize.dao.PermissionDao;
import com.speed.module.organize.dao.PersonDao;
import com.speed.module.organize.dao.RoleDao;
import com.speed.module.organize.entity.Permission;
import com.speed.module.organize.entity.Person;
import com.speed.module.organize.entity.Role;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义权限验证接口扩展
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    @Resource
    PermissionDao permissionDao;
    @Resource
    RoleDao roleDao;
    @Resource
    PersonDao personDao;


    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        Set<String> permissionKeySet = new HashSet<>();
        if(loginId!=null){
            //查询人员拥有的角色
            List<Role> roleList = roleDao.queryListByPsnId(loginId.toString());
            for(Role role : roleList){
                //查询角色拥有的权限
                List<Permission> permissionList = permissionDao.queryListByRoleId(role.getId());
                for(Permission permission : permissionList){
                    permissionKeySet.add(permission.getPermissionKey());
                }
            }
            //查询人员部门信息
            Person psn = personDao.getById(loginId.toString());
            if(psn!=null&& StrUtil.isNotBlank(psn.getDeptId())){
                //查询部门拥有的权限
                List<Permission> permissionList = permissionDao.queryListByDeptId(psn.getDeptId());
                for(Permission permission : permissionList){
                    permissionKeySet.add(permission.getPermissionKey());
                }
            }
        }
        return new ArrayList<>(permissionKeySet);
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        Set<String> roleKeySet = new HashSet<>();
        if(loginId!=null){
            //查询人员拥有的角色
            List<Role> roleList = roleDao.queryListByPsnId(loginId.toString());
            for(Role role : roleList){
                roleKeySet.add(role.getRoleKey());
            }
        }
        return new ArrayList<>(roleKeySet);
    }
}
