package com.speed.mutual.caffeine;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * caffeine缓存配置
 *
 * Caffeine配置说明：
 *
 * initialCapacity=[integer]: 初始的缓存空间大小
 * maximumSize=[long]: 缓存的最大条数
 * maximumWeight=[long]: 缓存的最大权重
 * expireAfterAccess=[duration]: 最后一次写入或访问后经过固定时间过期
 * expireAfterWrite=[duration]: 最后一次写入后经过固定时间过期
 * refreshAfterWrite=[duration]: 创建缓存或者最近一次更新缓存后经过固定的时间间隔，刷新缓存
 * weakKeys: 打开key的弱引用
 * weakValues：打开value的弱引用
 * softValues：打开value的软引用
 * recordStats：开发统计功能
 *
 * 注意：
 *
 * expireAfterWrite和expireAfterAccess同事存在时，以expireAfterWrite为准。
 * maximumSize和maximumWeight不可以同时使用
 * weakValues和softValues不可以同时使用
 *
 * @author joey
 */
@Configuration
@Slf4j
public class CaffeineConfig {
    /**
     * 10秒缓存
     */
    @Bean
    public Cache<String, Object> caffeineCache10s(){
        return Caffeine.newBuilder()
                .maximumSize(50000)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .build();
    }
    /**
     * 60秒缓存
     */
    @Bean
    public Cache<String, Object> caffeineCache60s(){
        return Caffeine.newBuilder()
                .maximumSize(50000)
                .expireAfterWrite(60, TimeUnit.SECONDS)
                .build();
    }
    /**
     * 30分钟缓存
     */
    @Bean
    public Cache<String, Object> caffeineCache30m(){
        return Caffeine.newBuilder()
                .maximumSize(50000)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build();
    }

    /**
     * 字典临时缓存
     */
    @Bean
    public Cache<String, Object> caffeineCacheDic(){
        return Caffeine.newBuilder()
                .maximumSize(50000)
                .expireAfterWrite(15, TimeUnit.SECONDS)
                .build();
    }
    /**
     * 权限缓存
     */
    @Bean
    public Cache<String, Object> caffeineCacheAuth(){
        return Caffeine.newBuilder()
                .maximumSize(50000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build();
    }
    /**
     * 属性缓存
     * joeLogEnable=true 开启日志追踪
     * joeLogShow=true 打印控制台日志
     * @return
     */
    @Bean
    public Cache<String, Object> caffeineCacheProperty(){
        return Caffeine.newBuilder()
                .maximumSize(1000)
                .build();
    }

}
