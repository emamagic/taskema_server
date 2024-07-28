package com.emamagic.taskema.config.authorization;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER(Set.of(
            Permission.USER_READ,
            Permission.USER_CREATE,
            Permission.USER_UPDATE,
            Permission.USER_DELETE
    )),
    WORKSPACE_ADMIN(Set.of(
            Permission.WORKSPACE_ADMIN_READ,
            Permission.WORKSPACE_ADMIN_CREATE,
            Permission.WORKSPACE_ADMIN_UPDATE,
            Permission.WORKSPACE_ADMIN_DELETE
    )),
    ORGANIZATION_ADMIN(Set.of(
            Permission.ORGANIZATION_ADMIN_READ,
            Permission.ORGANIZATION_ADMIN_CREATE,
            Permission.ORGANIZATION_ADMIN_UPDATE,
            Permission.ORGANIZATION_ADMIN_DELETE
    )),
    ;

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        return Collections.emptyList();
//        var authorities = getPermissions()
//                .stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                .toList();
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
//        return authorities;
    }
}
