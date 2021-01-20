package com.story.demo.authentification.entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;

@Getter
@Setter
@RedisHash("authTokens")
public class AuthToken {
    @Id
    private String id;

    @Indexed
    private String email;

    private String token;

    @Builder
    public AuthToken(String email, String token) {
        this.email = email;
        this.token = token;
    }

}
