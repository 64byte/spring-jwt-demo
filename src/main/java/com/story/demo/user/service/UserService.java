package com.story.demo.user.service;

import com.story.demo.user.dto.UserRegisterRequest;
import com.story.demo.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long registerUser(UserRegisterRequest userRegisterRequest) {

        return 123L;
    }

}
