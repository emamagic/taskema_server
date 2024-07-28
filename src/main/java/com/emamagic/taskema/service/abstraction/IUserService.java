package com.emamagic.taskema.service.abstraction;

import com.emamagic.taskema.model.dto.user.UserInfoResponse;
import com.emamagic.taskema.model.dto.user.UserInfoRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    UserInfoResponse getUserInfo(UserInfoRequest userInfo);

    List<UserInfoResponse> getAllUsers();

}
