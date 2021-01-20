package com.story.demo.authentification.dto;

import com.story.demo.authentification.entity.AuthToken;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthTokenRequest {
    private String email;
    private String token;

    public AuthToken toEntity() {
        return new AuthToken(this.email, this.token);
    }
}
