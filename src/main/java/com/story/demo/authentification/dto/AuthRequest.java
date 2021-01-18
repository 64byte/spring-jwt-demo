package com.story.demo.authentification.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

    private String email;

    private String password;

    @Builder
    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
