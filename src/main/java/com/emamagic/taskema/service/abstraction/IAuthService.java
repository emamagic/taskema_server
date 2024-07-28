package com.emamagic.taskema.service.abstraction;

import com.emamagic.taskema.model.dto.user.UserLoginRequest;
import com.emamagic.taskema.model.dto.user.UserLoginResponse;

public interface IAuthService {

    UserLoginResponse login(UserLoginRequest user);

    void logout(String userId);

}
