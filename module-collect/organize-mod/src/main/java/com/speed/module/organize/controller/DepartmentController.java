package com.speed.module.organize.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.speed.module.organize.dao.DepartmentDao;
import com.speed.module.organize.entity.Department;
import com.speed.mutual.common.dto.ResponseDTO;
import com.speed.mutual.common.utils.ResponseUtil;
import com.speed.mutual.webjpa.controller.BaseController;
import com.speed.mutual.webjpa.dao.BaseRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "部门管理")
@Slf4j
@RestController
@RequestMapping("/org/dept")
public class DepartmentController extends BaseController<Department> {
    @Resource
    DepartmentDao departmentDao;
    @Override
    public BaseRepository<Department, String> getService() {
        return departmentDao;
    }

    @ApiOperationSupport(order = 99904)
    @ApiOperation(value = "部门树形列表" )
    @GetMapping("/tree")
    @ApiImplicitParam(name="companyId",value="组织ID",dataType = "string", paramType = "query",required = true)
    public ResponseDTO tree(String companyId){
        try {
            Department query = new Department();
            query.setCompanyId(companyId);
            Example<Department> example = Example.of(query);
            List<Department> list = getService().findAll(example);
            List<Tree<String>> treeNodes = TreeUtil.build(list, "0", (one, tree) -> {
                tree.setId(one.getId());//必填属性
                tree.setParentId(one.getPid());//必填属性
                tree.putExtra("departmentName",one.getDepartmentName());
                tree.putExtra("departmentNo",one.getDepartmentNo());
                tree.putExtra("remarks",one.getRemarks());
                tree.putExtra("orderNo",one.getOrderNo());
                tree.putExtra("canceled",one.getCanceled());
            });
            return ResponseUtil.ok(treeNodes);
        }catch (Exception e){
            log.error("-查询部门树形列表-出错",e);
            return ResponseUtil.error(e.getMessage());
        }
    }
}
