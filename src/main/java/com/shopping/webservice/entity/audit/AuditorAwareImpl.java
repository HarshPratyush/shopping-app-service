package com.shopping.webservice.entity.audit;

import com.shopping.webservice.security.SecurityUtil;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;

import java.util.Optional;

public class AuditorAwareImpl  implements AuditorAware<String> {

    @Override
    @NonNull
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SecurityUtil.getAuthorId());
    }
}