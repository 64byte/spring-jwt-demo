package com.story.demo.user.dto;

import com.story.demo.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {

    private String email;

    private String password;

    public User toEntity() {
        return new User(email, password);
    }

}
