package com.story.demo.authentification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthResponse implements Serializable {

    @JsonProperty
    private String token;

    public static AuthResponse of(String token) {
        AuthResponse authResponse = new AuthResponse();
        authResponse.token = token;
        return authResponse;
    }

}
