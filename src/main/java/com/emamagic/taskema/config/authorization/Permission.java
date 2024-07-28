package com.emamagic.taskema.config.authorization;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    USER_READ("user:read"),
    USER_UPDATE("user:update"),
    USER_CREATE("user:create"),
    USER_DELETE("user:delete"),

    WORKSPACE_ADMIN_READ("workspace_admin:read"),
    WORKSPACE_ADMIN_UPDATE("workspace_admin:update"),
    WORKSPACE_ADMIN_CREATE("workspace_admin:create"),
    WORKSPACE_ADMIN_DELETE("workspace_admin:delete"),

    ORGANIZATION_ADMIN_READ("organization_admin:read"),
    ORGANIZATION_ADMIN_UPDATE("organization_admin:update"),
    ORGANIZATION_ADMIN_CREATE("organization_admin:create"),
    ORGANIZATION_ADMIN_DELETE("organization_admin:delete")
    ;

    private final String permission;
}
