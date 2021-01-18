package com.story.demo.user.service;

import com.story.demo.user.dto.UserRegisterRequest;
import com.story.demo.user.exception.AlreadyRegisteredUserException;
import com.story.demo.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long registerUser(UserRegisterRequest userRegisterRequest) throws Exception {
        if (isAlreadyRegistered(userRegisterRequest.getEmail())) {
            throw new AlreadyRegisteredUserException();
        }

        return userRepository.save(userRegisterRequest.toEntity()).getUserId();
    }

    public boolean isAlreadyRegistered(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
