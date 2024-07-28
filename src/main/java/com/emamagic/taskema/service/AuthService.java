package com.emamagic.taskema.service;

import com.emamagic.taskema.config.locale.message.ResponseMessageCode;
import com.emamagic.taskema.config.locale.message.ResponseMessageUtils;
import com.emamagic.taskema.dao.UserDao;
import com.emamagic.taskema.model.dto.response.RichError;
import com.emamagic.taskema.model.dto.user.UserLoginRequest;
import com.emamagic.taskema.model.dto.user.UserLoginResponse;
import com.emamagic.taskema.model.entity.UserEntity;
import com.emamagic.taskema.service.abstraction.IAuthService;
import com.emamagic.taskema.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class AuthService implements IAuthService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserLoginResponse login(UserLoginRequest user) {
        try {
            var userEntity = userDao.save(
                    UserEntity.builder()
                            .username(user.username())
                            .hashedPassword(passwordEncoder.encode(user.password()))
                            .organizationId(user.organizationId())
                            .build()
            );
            return new UserLoginResponse(userEntity.getId(), userEntity.getUsername(), userEntity.getRole(), userEntity.getCreatedAt());
        } catch (RuntimeException exception) {
            throw RichError.builder()
                    .operation(AppUtil.getOperationName(AuthService.class))
                    .nestedError(exception)
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .meta(Collections.singletonList(user.toString()))
                    .message(ResponseMessageUtils.getMessage(ResponseMessageCode.USER_DUPLICATE_FOUND_EXCEPTION))
                    .build();
        }
    }

    @Override
    public void logout(String userId) {
        userDao.deleteById(userId);
    }

}
