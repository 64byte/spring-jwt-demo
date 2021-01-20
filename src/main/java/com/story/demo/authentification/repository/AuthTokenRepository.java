package com.story.demo.authentification.repository;

import com.story.demo.authentification.entity.AuthToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthTokenRepository extends CrudRepository<AuthToken, String> {
    Optional<AuthToken> findByEmail(String email);
    void deleteByEmail(String email);
}
