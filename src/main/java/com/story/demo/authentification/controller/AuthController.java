package com.story.demo.authentification.controller;

import com.story.demo.authentification.dto.AuthRequest;
import com.story.demo.authentification.dto.AuthResponse;
import com.story.demo.authentification.provider.JwtProvider;
import com.story.demo.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtProvider jwtProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> authUser(AuthRequest authRequest) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

            String token = jwtProvider.generateToken(authRequest.getEmail());

            return new ResponseEntity<>(AuthResponse.of(token), HttpStatus.CREATED);
        } catch (AuthenticationException ae) {
            throw new BadCredentialsException("");
        }

    }

}
