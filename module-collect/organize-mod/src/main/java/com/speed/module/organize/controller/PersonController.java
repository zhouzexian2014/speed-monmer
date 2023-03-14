package com.speed.module.organize.controller;

import com.speed.module.organize.dao.PersonDao;
import com.speed.module.organize.entity.Person;
import com.speed.mutual.webjpa.controller.BaseController;
import com.speed.mutual.webjpa.dao.BaseRepository;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "人员管理")
@Slf4j
@RestController
@RequestMapping("/org/psn")
public class PersonController extends BaseController<Person> {
    @Resource
    PersonDao personDao;

    @Override
    public BaseRepository<Person, String> getService() {
        return personDao;
    }
}
