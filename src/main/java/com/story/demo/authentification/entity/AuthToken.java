package com.story.demo.authentification.entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@Setter
@RedisHash("authTokens")
public class AuthToken {
    @Id
    private String id;

    private String email;

    private String token;

    @Builder
    public AuthToken(String email, String token) {
        this.email = email;
        this.token = token;
    }
}
