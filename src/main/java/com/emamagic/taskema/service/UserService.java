package com.emamagic.taskema.service;

import com.emamagic.taskema.config.locale.message.ResponseMessageCode;
import com.emamagic.taskema.config.locale.message.ResponseMessageUtils;
import com.emamagic.taskema.dao.UserDao;
import com.emamagic.taskema.model.dto.response.RichError;
import com.emamagic.taskema.model.dto.user.UserInfoRequest;
import com.emamagic.taskema.model.dto.user.UserInfoResponse;
import com.emamagic.taskema.model.security.UserSecure;
import com.emamagic.taskema.service.abstraction.IUserService;
import com.emamagic.taskema.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final UserDao userDao;

    @Override
    public UserInfoResponse getUserInfo(UserInfoRequest userInfo) {
        if (userInfo.id() == null) {
            String username = ((UserSecure) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
            var userEntity = userDao.findByUsername(username).orElseThrow(() -> RichError.builder()
                            .operation(AppUtil.getOperationName(UserSecure.class))
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .message(ResponseMessageUtils.getMessage(ResponseMessageCode.USER_NOT_FOUND_EXCEPTION))
                            .meta(Collections.singletonList(userInfo.toString()))
                            .build()
                    );
            return new UserInfoResponse(userEntity.getId(), userEntity.getUsername(), userEntity.getRole(), userEntity.getCreatedAt());
        }
        var userEntity = userDao.findById(userInfo.id()).orElseThrow(() -> RichError.builder()
                .operation(AppUtil.getOperationName(UserSecure.class))
                .message(ResponseMessageUtils.getMessage(ResponseMessageCode.USER_NOT_FOUND_EXCEPTION))
                .httpStatus(HttpStatus.NOT_FOUND)
                .meta(Collections.singletonList(userInfo.toString()))
                .build());
        return new UserInfoResponse(userEntity.getId(), userEntity.getUsername(), userEntity.getRole(), userEntity.getCreatedAt());
    }

    @Override
    public List<UserInfoResponse> getAllUsers() {
        return userDao.findAll().stream().map(entity ->
                new UserInfoResponse(entity.getId(), entity.getUsername(), entity.getRole(), entity.getCreatedAt())).toList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
         var userEntity = userDao.findByUsername(username).orElseThrow(() -> RichError.builder()
                 .operation(AppUtil.getOperationName(UserSecure.class))
                 .message(ResponseMessageUtils.getMessage(ResponseMessageCode.USER_NOT_FOUND_EXCEPTION))
                 .httpStatus(HttpStatus.NOT_FOUND)
                 .meta(Collections.singletonList(username))
                 .build());
         return new UserSecure(userEntity);
    }
}
