package com.speed.mutual.webjpa.service;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * ID生成策略
 */
public class IdGengerator implements IdentifierGenerator {
    private Snowflake snowflake = IdUtil.getSnowflake(1, 1);

    public IdGengerator(){}

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return snowflake.nextIdStr();
    }
}
