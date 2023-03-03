package com.speed.mutual.webjpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public  interface  BaseRepository<T,V> extends JpaRepository<T,V>, JpaSpecificationExecutor<T> {
}
