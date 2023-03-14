package com.speed.module.system.consumer;

import com.speed.module.system.dao.TraceLogDao;
import com.speed.module.system.entity.TraceLog;
import com.speed.mutual.common.utils.SpringContextUtil;
import com.speed.mutual.disruptor.ADisruptorConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 访问日志收集
 */
public class LogConsumer extends ADisruptorConsumer<TraceLog> {
    protected Logger logger= LoggerFactory.getLogger(LogConsumer.class);
    TraceLogDao traceLogDao = SpringContextUtil.getBean(TraceLogDao.class);
    @Override
    public void consume(TraceLog var) {
        traceLogDao.save(var);
    }
}
