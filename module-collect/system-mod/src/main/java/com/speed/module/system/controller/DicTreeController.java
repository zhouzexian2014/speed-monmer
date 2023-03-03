package com.speed.module.system.controller;


import com.speed.mutual.webjpa.controller.BaseController;
import com.speed.mutual.webjpa.dao.BaseRepository;
import com.speed.module.system.dao.DicTreeDao;
import com.speed.module.system.entity.DicTree;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 字典树信息 前端控制器
 * </p>
 *
 * @author Joey
 * @since 2022-09-18
 */
@Api(tags = "字典-树")
@Slf4j
@RestController
@RequestMapping("/sys/dic-tree")
public class DicTreeController extends BaseController<DicTree> {
    @Resource
    DicTreeDao dicTreeDao;

    @Override
    public BaseRepository<DicTree,String> getService() {
        return dicTreeDao;
    }
}

