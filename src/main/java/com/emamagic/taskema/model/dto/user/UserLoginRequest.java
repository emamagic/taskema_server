package com.emamagic.taskema.model.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginRequest(
        @NotBlank(message = "username could not be empty")
        String username,
        @NotBlank
        @Size(min = 3, message = "min password length is 3")
        String password,
        @NotBlank(message = "organizationId could not empty")
        String organizationId
) {
}
