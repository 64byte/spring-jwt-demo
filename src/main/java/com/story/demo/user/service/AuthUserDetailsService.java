package com.story.demo.user.service;

import com.story.demo.authentification.service.AuthTokenService;
import com.story.demo.user.entity.AuthUserDetails;
import com.story.demo.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * userService에 UserDetailsService를 상속 시에 순환 참조가 일어남.
 */
@Component
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).map(user -> AuthUserDetails.of(user, "")).orElseThrow(() -> new UsernameNotFoundException(""));
    }
}
