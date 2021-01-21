package com.story.demo.authentification.provider;

import com.story.demo.authentification.exception.InvalidJwtAuthenticationException;
import com.story.demo.authentification.service.AuthTokenService;
import com.story.demo.user.entity.AuthUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class JwtProvider {

    private static final String secret = "fd4db9644040cb8231cf7fb727a7ff23a85b985da450c0c840976127c9c0adfe0ef9a4f7e88ce7a1585dd59cf78f0ea57535d6b1cd744c1ee62d726572f51432";

    private static final Key secretKey = Keys.hmacShaKeyFor(secret.getBytes());

    private static final long termOfExpiration = 3600000;

    private final UserDetailsService userDetailsService;

    public JwtProvider(@Qualifier("authUserDetailsService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public static String generateToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);

        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        Timestamp expr = new Timestamp(now.getTime() + termOfExpiration);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expr)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public static String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts
                    .parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(Timestamp.valueOf(LocalDateTime.now()));
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }
}
