package com.emamagic.taskema.model.entity;

import com.emamagic.taskema.config.authorization.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "users")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false, unique = true, length = 12)
    String username;

    @Column(nullable = false)
    @Size(min = 3)
    String hashedPassword;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private String organizationId;

    @Column
    String avatar;

    @Column(columnDefinition = "TIMESTAMPTZ")
    Instant createdAt;

    @Column(columnDefinition = "TIMESTAMPTZ")
    Instant updatedAt;


    @PrePersist
    public void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
        if (role == null) role = Role.USER;
    }

}

