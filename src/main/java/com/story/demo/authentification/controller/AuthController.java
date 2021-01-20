package com.story.demo.authentification.controller;

import com.story.demo.authentification.dto.AuthRequest;
import com.story.demo.authentification.dto.AuthResponse;
import com.story.demo.authentification.provider.JwtProvider;
import com.story.demo.authentification.service.AuthTokenService;
import com.story.demo.user.controller.UserController;
import com.story.demo.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserController userController;

    private final AuthTokenService authTokenService;

    public AuthController(AuthenticationManager authenticationManager, UserController userController, AuthTokenService authTokenService) {
        this.authenticationManager = authenticationManager;
        this.userController = userController;
        this.authTokenService = authTokenService;
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity<AuthResponse> authUser(@RequestBody AuthRequest authRequest) {

        try {
            String email = authRequest.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, authRequest.getPassword()));

            String token = authTokenService.issueToken(email);

            return new ResponseEntity<>(AuthResponse.of(token), HttpStatus.CREATED);
        } catch (AuthenticationException ae) {
            throw new BadCredentialsException("");
        }
    }

}
