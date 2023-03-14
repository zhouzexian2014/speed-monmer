package com.speed.module.organize.conf;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * JPA获取当前人
 */
@Component
public class JpaUserAuditor implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String loginId = StpUtil.getLoginId("狗长老");
        return Optional.of(loginId);
    }
}
