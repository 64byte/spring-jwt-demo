package com.story.demo.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.story.demo.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
public class UserRegisterRequest {

    private String email;

    private String password;

    public User toEntity() {
        return new User(email, password);
    }

}
