package com.example.consumption.global;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {
    private final long expiration;
    private final SecretKey key;

    public JwtUtils(
            @Value("${jwt.expiration}") long expiration,
            @Value("${jwt.secret}") String secret) {
        this.expiration = expiration;
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    //   generate-token
    public String generateToken(String userId){
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);
        return Jwts.builder()
                .subject(userId)
                .expiration(expirationDate)
                .signWith(key)
                .compact();
    }
    //   parse-token
    public String parseToken(String token){
        Claims payload = (Claims) Jwts.parser()
                .verifyWith(key)
                .build()
                .parse(token)
                .getPayload();
        return payload.getSubject();
    }

}