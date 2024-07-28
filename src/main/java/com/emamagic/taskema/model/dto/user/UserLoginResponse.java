package com.emamagic.taskema.model.dto.user;

import com.emamagic.taskema.config.authorization.Role;

import java.time.Instant;

public record UserLoginResponse (
        String id,
        String username,
        Role role,
        Instant createdAt
) {
}
