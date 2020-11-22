package com.shopping.webservice.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtil {
    public static String getAuthorId() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(
                        user -> {
                            Object principal = user.getPrincipal();
                            if (principal instanceof String) {
                                return principal.toString();
                            } else {
                                return ((JwtUserDetails) (principal)).getUsername();
                            }
                        })
                .orElse("GUEST");
    }

    public static long getUserID() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object additionalDetails = authentication.getPrincipal();
        if (additionalDetails instanceof JwtUserDetails) {
            return ((JwtUserDetails) additionalDetails).getId();
        } else {
            throw new AccessDeniedException("Not Authenticated");
        }
    }

    public static Stream<String> getGrantedAuthority(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority);
    }
}
