package com.emamagic.taskema.dao;

import com.emamagic.taskema.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByUsername(String username);

}
