package com.story.demo.authentification.service;

import com.story.demo.authentification.dto.AuthTokenRequest;
import com.story.demo.authentification.entity.AuthToken;
import com.story.demo.authentification.repository.AuthTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {

    private final AuthTokenRepository authTokenRepository;

    public AuthTokenService(AuthTokenRepository authTokenRepository) {
        this.authTokenRepository = authTokenRepository;
    }

    public String saveToken(String email, String token) {
        return this.authTokenRepository.save(new AuthToken(email, token)).getId();
    }
}
