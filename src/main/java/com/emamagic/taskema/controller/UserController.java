package com.emamagic.taskema.controller;

import com.emamagic.taskema.config.locale.message.ResponseMessageCode;
import com.emamagic.taskema.config.locale.message.ResponseMessageUtils;
import com.emamagic.taskema.model.dto.response.ApiResponse;
import com.emamagic.taskema.model.dto.user.UserInfoRequest;
import com.emamagic.taskema.model.dto.user.UserInfoResponse;
import com.emamagic.taskema.service.abstraction.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService userSvc;

    @GetMapping
    public ApiResponse<UserInfoResponse> getUser(@RequestParam(name = "userId", required = false) String userId) {
        return ApiResponse.<UserInfoResponse>builder()
                .httpStatus(HttpStatus.OK)
                .message(ResponseMessageUtils.getMessage(ResponseMessageCode.SUCCESS))
                .payload(userSvc.getUserInfo(new UserInfoRequest(userId)))
                .build();
    }

    @GetMapping("/all")
    public ApiResponse<List<UserInfoResponse>> getAllUsers() {
        return ApiResponse.<List<UserInfoResponse>>builder()
                .httpStatus(HttpStatus.OK)
                .message(ResponseMessageUtils.getMessage(ResponseMessageCode.SUCCESS))
                .payload(userSvc.getAllUsers())
                .build();
    }

}
