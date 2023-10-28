package com.example.villion_auth_service.config;

import com.example.villion_auth_service.domain.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    public String makeToken(Member member) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("loginId", member.getLoginId());
        claims.put("email", member.getEmail());
        claims.put("libraryName", member.getLibraryName());

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(member.getLoginId())
                .setExpiration(new Date(
                        System.currentTimeMillis()
                                + (1000L * 60 * 60 * 5) // 5시간
                ))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
        return token;

    }

    public TokenInfo parseToken(String token) {
        Claims body = (Claims) Jwts.parser()
                .setSigningKey(secret.getBytes())
                .build()
                .parse(token)
                .getBody();
        return TokenInfo.builder()
                .loinId(body.get("loginId", String.class))
                .email(body.get("email", String.class))
                .libraryName(body.get("libraryName", String.class))
                .build();
    }
}
