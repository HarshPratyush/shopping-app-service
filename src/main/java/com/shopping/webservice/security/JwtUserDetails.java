package com.shopping.webservice.security;

import com.shopping.webservice.entity.UserEntity;
import com.shopping.webservice.enums.AuthenticationProvider;
import com.shopping.webservice.enums.UserType;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
public class JwtUserDetails implements OAuth2User, UserDetails, Serializable {
    private static final long serialVersionUID = 0L;
    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;
    private UserType userType;
    private AuthenticationProvider authenticationProvider;

    public JwtUserDetails(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities,UserType userType, AuthenticationProvider authenticationProvider) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.userType = userType;
        this.authenticationProvider = authenticationProvider;
    }

    public static JwtUserDetails create(UserEntity user) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority(user.getUserType().name()));

        return new JwtUserDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                authorities,
                user.getUserType(),
                user.getProvider()
        );
    }

    public static JwtUserDetails create(UserEntity user, Map<String, Object> attributes) {
        JwtUserDetails userPrincipal = JwtUserDetails.create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }
}
