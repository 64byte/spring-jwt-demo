package com.story.demo.user.controller;

import com.story.demo.user.dto.UserRegisterRequest;
import com.story.demo.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Long> registerUser(UserRegisterRequest userRegisterRequest) {
        try {
            return new ResponseEntity<Long>(userService.registerUser(userRegisterRequest), HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
        }
    }

}
