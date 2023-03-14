package com.speed.module.organize.dao;

import com.speed.module.organize.entity.Permission;
import com.speed.module.organize.entity.Person;
import com.speed.mutual.webjpa.dao.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonDao extends BaseRepository<Person,String> {

    /**
     * 查询部门的人员
     * @param deptId 部门id
     * @return
     */
    @Query(value = "select t1 from Person t1 left join RelPersonDept t2 on t1.id=t2.personId where t2.deptId=?1")
    List<Person> queryListByDeptId(String deptId);

}
