package com.story.demo.user.service;

import com.story.demo.user.dto.UserRegisterRequest;
import com.story.demo.user.exception.AlreadyRegisteredUserException;
import com.story.demo.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 새로운 유저를 등록합니다.
     * @param userRegisterRequest
     * @return
     */
    public long registerUser(UserRegisterRequest userRegisterRequest) {
        if (isAlreadyRegistered(userRegisterRequest.getEmail())) {
            throw new AlreadyRegisteredUserException();
        }

        userRegisterRequest.setPassword(this.passwordEncoder.encode(userRegisterRequest.getPassword()));

        return userRepository.save(userRegisterRequest.toEntity()).getUserId();
    }

    /**
     * 해당 email이 이미 등록되어 있는지 확인합니다.
     * @param email
     * @return
     */
    public boolean isAlreadyRegistered(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
