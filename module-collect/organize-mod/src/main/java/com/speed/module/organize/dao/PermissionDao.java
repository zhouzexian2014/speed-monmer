package com.speed.module.organize.dao;

import com.speed.module.organize.entity.Permission;
import com.speed.mutual.webjpa.dao.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionDao extends BaseRepository<Permission,String> {
    /**
     * 查询部门的权限
     * @param deptId 部门id
     * @return
     */
    @Query(value = "select t1 from Permission t1 left join RelDeptPermission t2 on t1.id=t2.permissionId where t2.deptId=?1")
    List<Permission> queryListByDeptId(String deptId);
    /**
     * 查询角色的权限
     * @param roleId 角色id
     * @return
     */
    @Query(value = "select t1 from Permission t1 left join RelRolePermission t2 on t1.id=t2.permissionId where t2.roleId=?1")
    List<Permission> queryListByRoleId(String roleId);



}
