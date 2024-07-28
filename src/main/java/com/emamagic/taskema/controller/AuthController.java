package com.emamagic.taskema.controller;

import com.emamagic.taskema.config.locale.message.ResponseMessageCode;
import com.emamagic.taskema.config.locale.message.ResponseMessageUtils;
import com.emamagic.taskema.model.dto.response.ApiResponse;
import com.emamagic.taskema.model.dto.user.UserLoginRequest;
import com.emamagic.taskema.model.dto.user.UserLoginResponse;
import com.emamagic.taskema.service.abstraction.IAuthService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final IAuthService authSvc;

    @PostMapping("/login")
    public ApiResponse<UserLoginResponse> login(@Validated @RequestBody UserLoginRequest req) {
        var resp = authSvc.login(req);
        return ApiResponse.<UserLoginResponse>builder()
                .httpStatus(HttpStatus.OK)
                .message(ResponseMessageUtils.getMessage(ResponseMessageCode.SUCCESS))
                .payload(resp)
                .build();
    }

    @PostMapping("/logout/{userId}")
    public ApiResponse<Void> logout(@NotBlank @PathVariable String userId) {
        authSvc.logout(userId);
        return ApiResponse.<Void>builder()
                .httpStatus(HttpStatus.OK)
                .message(ResponseMessageUtils.getMessage(ResponseMessageCode.SUCCESS))
                .build();
    }

}
