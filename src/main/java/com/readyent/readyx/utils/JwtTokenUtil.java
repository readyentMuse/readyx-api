package com.readyent.readyx.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;  // application.yml에서 secret 값을 가져옴

    @Value("${jwt.expiration}")
    private long expirationTime;  // application.yml에서 expiration 값을 가져옴

    private Key secretKey;

    // secretKey 초기화
    @PostConstruct
    public void init() {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        this.secretKey = Keys.hmacShaKeyFor(decodedKey);
    }

    // 토큰 생성 메서드
    public String generateToken(String phoneNumber) {
//        log.debug("phone number: {}", phoneNumber);
        return Jwts.builder()
                .setSubject(phoneNumber)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    // 시크릿 키 getter
    public Key getSecretKey() {
        return secretKey;
    }
}
