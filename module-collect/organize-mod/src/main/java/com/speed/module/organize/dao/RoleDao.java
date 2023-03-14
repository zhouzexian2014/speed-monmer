package com.speed.module.organize.dao;


import com.speed.module.organize.entity.Role;
import com.speed.mutual.webjpa.dao.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleDao extends BaseRepository<Role,String> {

    /**
     * 查询人员的角色
     * @param psnId 人员id
     * @return
     */
    @Query(value = "select t1 from Role t1 left join RelPersonRole t2 on t1.id=t2.roleId where t2.personId=?1")
    List<Role> queryListByPsnId(String psnId);

}
