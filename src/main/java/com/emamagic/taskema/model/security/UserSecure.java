package com.emamagic.taskema.model.security;

import com.emamagic.taskema.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@ToString
@RequiredArgsConstructor
public class UserSecure implements UserDetails {

    private final UserEntity userEntity;

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override
    public String getPassword() {
        return userEntity.getHashedPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userEntity.getRole().getAuthorities();
    }
}
