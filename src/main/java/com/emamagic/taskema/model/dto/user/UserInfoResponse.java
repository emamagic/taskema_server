package com.emamagic.taskema.model.dto.user;

import com.emamagic.taskema.config.authorization.Role;

import java.time.Instant;

public record UserInfoResponse(
        String id,
        String username,
        Role role,
        Instant createdAt
) {
}
