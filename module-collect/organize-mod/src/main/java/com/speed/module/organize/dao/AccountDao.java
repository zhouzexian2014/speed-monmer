package com.speed.module.organize.dao;

import com.speed.module.organize.entity.Account;
import com.speed.module.organize.entity.Person;
import com.speed.mutual.webjpa.dao.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountDao extends BaseRepository<Account,String> {

    Account findByUsername(String username);
}
