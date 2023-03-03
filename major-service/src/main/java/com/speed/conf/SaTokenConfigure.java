package com.speed.conf;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册Sa-Token的注解拦截器，打开注解式鉴权功能
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
        registry.addInterceptor(new SaRouteInterceptor())
                //.addPathPatterns("/**")
                .excludePathPatterns("/**");

    }
}