package com.speed.module.project.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.speed.module.project.dao.TaskInfoDao;
import com.speed.module.project.entity.TaskInfo;
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

@Api(tags = "任务管理")
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

    @ApiOperationSupport(order = 99904)
    @ApiOperation(value = "任务树形列表" )
    @GetMapping("/tree")
    @ApiImplicitParam(name="projectId",value="项目ID",dataType = "string", paramType = "query",required = true)
    public ResponseDTO tree(String projectId){
        try {
            TaskInfo queryTask = new TaskInfo();
            queryTask.setProjectId(projectId);
            Example<TaskInfo> example = Example.of(queryTask);
            List<TaskInfo> list = getService().findAll(example);
            List<Tree<String>> treeNodes = TreeUtil.build(list, "0", (task, tree) -> {
                tree.setId(task.getId());//必填属性
                tree.setParentId(task.getPid());//必填属性
                tree.putExtra("taskNo",task.getTaskNo());
                tree.putExtra("taskTitle",task.getTaskTitle());
                tree.putExtra("taskContent",task.getTaskContent());
                tree.putExtra("taskProgress",task.getTaskProgress());
                tree.putExtra("planStartTime",task.getPlanStartTime());
                tree.putExtra("planEndTime",task.getPlanEndTime());
                tree.putExtra("actualStartTime",task.getActualStartTime());
                tree.putExtra("actualEndTime",task.getActualEndTime());
                tree.putExtra("taskStatus",task.getTaskStatus());
            });
            return ResponseUtil.ok(treeNodes);
        }catch (Exception e){
            log.error("-查询任务树形列表-出错",e);
            return ResponseUtil.error(e.getMessage());
        }
    }
}
