package com.speed.module.system.controller;


import com.speed.mutual.webjpa.controller.BaseController;
import com.speed.mutual.webjpa.dao.BaseRepository;
import com.speed.module.system.dao.DicTreeItemDao;
import com.speed.module.system.entity.DicTreeItem;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 字典项信息 前端控制器
 * </p>
 *
 * @author Joey
 * @since 2022-09-18
 */
@Api(tags = "字典-项")
@Slf4j
@RestController
@RequestMapping("/sys/dic-tree-item")
public class DicTreeItemController extends BaseController<DicTreeItem> {
    @Resource
    DicTreeItemDao dicTreeItemDao;

    @Override
    public BaseRepository<DicTreeItem,String> getService() {
        return dicTreeItemDao;
    }
}

