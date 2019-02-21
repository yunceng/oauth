package com.wyn.common.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User {

    private UserEntity user;

    public CustomUserDetails(UserEntity user, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), true, true, credentialsNonExpired, accountNonLocked, authorities);
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
