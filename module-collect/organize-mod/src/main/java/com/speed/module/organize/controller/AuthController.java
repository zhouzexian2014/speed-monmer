package com.speed.module.organize.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.speed.module.organize.dao.AccountDao;
import com.speed.module.organize.dao.PersonDao;
import com.speed.module.organize.entity.Account;
import com.speed.module.organize.entity.Person;
import com.speed.module.organize.form.PwdLoginForm;
import com.speed.mutual.common.dto.ResponseDTO;
import com.speed.mutual.common.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

@Api(tags = "授权管理")
@Slf4j
@RestController
@RequestMapping("/org/auth")
public class AuthController {
    @Resource
    AccountDao accountDao;

    @ApiOperationSupport(order = 99901)
    @ApiOperation(value = "登录-密码模式" )
    @PostMapping("/pwdLogin")
    public ResponseDTO pwdLogin(@RequestBody PwdLoginForm form){
        try {
            Account account = accountDao.findByUsername(form.getUsername().toLowerCase().trim());
            if(account!=null){
                if(account.getCanceled()){
                    return ResponseUtil.error("账号已被禁用！");
                }
                String pwd = SecureUtil.md5(form.getPassword().trim());
                if(account.getPassword().equals(pwd)){
                    StpUtil.login(account.getPersonId());
                    return ResponseUtil.ok(StpUtil.getTokenValue(),"登录成功！");
                }
            }
            return ResponseUtil.error("账号或密码不正确！");
        }catch (Exception e){
            log.error("--登录-密码模式-出错",e);
            log.error( JSONUtil.toJsonPrettyStr(form));
            return ResponseUtil.error(e.getMessage());
        }
    }

}
