package com.story.demo.authentification.repository;

import com.story.demo.authentification.entity.AuthToken;
import org.springframework.data.repository.CrudRepository;

public interface AuthTokenRepository extends CrudRepository<AuthToken, String> {

}
