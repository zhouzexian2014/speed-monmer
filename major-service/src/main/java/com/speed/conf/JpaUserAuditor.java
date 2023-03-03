package com.speed.conf;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * JPA获取当前人
 */
public class JpaUserAuditor implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("狗长老");
    }
}
