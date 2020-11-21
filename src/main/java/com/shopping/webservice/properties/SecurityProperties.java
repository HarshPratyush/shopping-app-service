package com.shopping.webservice.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
@Getter
@RequiredArgsConstructor
public class SecurityProperties {

    private final int passwordStrength;
    /**
     * Secret used to generate and verify JWT tokens
     */
    private final String secret;
    /**
     * Name of the token issuer
     */
    private final String issuer;
    /**
     * Duration after which a token will expire
     */
    private final Duration tokenExpiration = Duration.ofHours(4);

    private List<String> authorizedRedirectUris = new ArrayList<>();
}
