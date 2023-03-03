package com.speed.module.system.controller;


import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.speed.mutual.common.dto.ResponseDTO;
import com.speed.mutual.common.utils.ResponseUtil;
import com.speed.mutual.webjpa.controller.BaseController;
import com.speed.mutual.webjpa.dao.BaseRepository;
import com.speed.module.system.dao.MenuDao;
import com.speed.module.system.entity.Menu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 菜单信息 前端控制器
 * </p>
 *
 * @author Joey
 * @since 2022-09-27
 */
@Api(tags = "菜单")
@RestController
@RequestMapping("/sys/menu")
@Slf4j
public class MenuController extends BaseController<Menu> {
    @Resource
    MenuDao menuDao;
    @Override
    public BaseRepository<Menu,String> getService() {
        return menuDao;
    }

    @ApiOperationSupport(order = 99904)
    @ApiOperation(value = "通用树形菜单" )
    @GetMapping("/tree")
    public ResponseDTO tree(){
        try {
            List<Menu> list = getService().findAll();
            //TreeNodeConfig config = new TreeNodeConfig();
            List<Tree<String>> treeNodes = TreeUtil.build(list, "0", (menu, tree) -> {
                tree.setId(menu.getId());//必填属性
                tree.setParentId(menu.getPid());//必填属
                tree.putExtra("menuName",menu.getMenuName());
                tree.putExtra("extend1",menu.getExtend1());
            });
            return ResponseUtil.ok(treeNodes);
        }catch (Exception e){
            log.error("-查询菜单树形-出错",e);
            return ResponseUtil.error(e.getMessage());
        }
    }

}

