package com.speed.module.organize.controller;

import com.speed.module.organize.dao.CompanyDao;
import com.speed.module.organize.entity.Company;
import com.speed.mutual.webjpa.controller.BaseController;
import com.speed.mutual.webjpa.dao.BaseRepository;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "组织管理")
@Slf4j
@RestController
@RequestMapping("/org/company")
public class CompanyController extends BaseController<Company> {
    @Resource
    CompanyDao companyDao;

    @Override
    public BaseRepository<Company, String> getService() {
        return companyDao;
    }
}
