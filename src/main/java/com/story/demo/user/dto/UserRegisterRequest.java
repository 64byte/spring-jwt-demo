package com.story.demo.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.story.demo.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserRegisterRequest {

    @Email
    private String email;

    @NotBlank
    private String password;

    public User toEntity() {
        return new User(email, password);
    }

}
