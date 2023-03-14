package com.speed.module.system.aspect;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import com.speed.module.system.consumer.LogConsumer;
import com.speed.module.system.entity.TraceLog;
import com.speed.mutual.common.annotation.IgnoreLog;
import com.speed.mutual.common.utils.DevUtils;
import com.speed.mutual.disruptor.DisruptorQueue;
import com.speed.mutual.disruptor.DisruptorQueueFactory;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import sun.net.util.IPAddressUtil;

import java.time.LocalDateTime;

/**
 * AOP切面-访问日志打印
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    DisruptorQueue logDisruptorQueue = DisruptorQueueFactory.getWorkPoolQueue(1024,
            false, new LogConsumer());

    @Pointcut("execution(public * com.speed..*Controller.*(..))")
    public void handle() {}

    @Around("handle()")
    public Object action(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        ApiOperation apiOperation = signature.getMethod().getAnnotation(ApiOperation.class);
        IgnoreLog ignoreLog = signature.getMethod().getAnnotation(IgnoreLog.class);
        if(apiOperation!=null&&ignoreLog==null){
            TraceLog traceLog = new TraceLog();
            try {
                long start = System.currentTimeMillis();
                Object result = joinPoint.proceed();
                long end = System.currentTimeMillis();
                traceLog.setDuration(end-start);
                traceLog.setResultContent(DevUtils.limitStr(DevUtils.toJSONString(result),2000));
                return result;
            }catch (Throwable e){
                traceLog.setSuccess(false);
                traceLog.setException(DevUtils.limitStr(e.getMessage(),1024));
                throw new Throwable(e);
            }finally {
                traceLog.setId(IdUtil.fastSimpleUUID());
                traceLog.setTraceId(traceLog.getId());
                traceLog.setName(apiOperation.value());
                traceLog.setClassName(signature.getDeclaringType().getName());
                traceLog.setMethodName(signature.getName());
                traceLog.setParamContent(DevUtils.limitStr(DevUtils.toJSONString(joinPoint.getArgs()),1000));
                traceLog.setUrl(DevUtils.getRequestURI());
                traceLog.setStartTime(LocalDateTime.now());
                traceLog.setCreateBy(StpUtil.getLoginIdAsString());
                //traceLog.setIp(AddrUtil.getLocalAddr());
                logDisruptorQueue.add(traceLog);
            }
        }
        return joinPoint.proceed();
    }
}
