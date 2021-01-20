package com.story.demo.authentification.service;

import com.story.demo.authentification.entity.AuthToken;
import com.story.demo.authentification.provider.JwtProvider;
import com.story.demo.authentification.repository.AuthTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {

    private final AuthTokenRepository authTokenRepository;

    private final JwtProvider jwtProvider;

    public AuthTokenService(AuthTokenRepository authTokenRepository, JwtProvider jwtProvider) {
        this.authTokenRepository = authTokenRepository;
        this.jwtProvider = jwtProvider;
    }

    /**
     * controller에서 인증이 완료된 유저에게 토큰을 발행한다.
     * 이미 발행되었다면 기존 토큰을 반환하고, 없다면 새롭게 생성하여 반환한다.
     * @param email
     * @return
     */
    public String issueToken(String email) {
        return authTokenRepository.findByEmail(email)
                .orElseGet(() -> saveToken(email, jwtProvider.generateToken(email)))
                .getToken();
    }

    /**
     * 만료 요청 시에 발급된 토큰을 지운다.
     * @param authTokenId
     */
    public void dismissTokenById(String authTokenId) {
        this.authTokenRepository.deleteById(authTokenId);
    }

    /**
     * 토큰을 저장한다. (email, token)
     * @param email
     * @param token
     * @return
     */
    public AuthToken saveToken(String email, String token) {
        return this.authTokenRepository.save(new AuthToken(email, token));
    }
}
