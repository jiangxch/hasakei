package com.github.jiangxch.hasakei.config.manager;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenManager {

    public String HEADER = "token";

    private String SECRET="eyJleHAiOjE1NDMyMDUyODUsInN1YiI6ImFkbWluIiwiY3JlYXRlZCI6MTU0MDYxMzI4N";

    public Integer EXPIRATION=3600000;

    public String createToken(String username) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION);
        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        return jwt;
    }

    public String getUsernameFromJwt(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwt).getBody();
        return claims.getSubject();
    }

    public void validateToken(String token) {
        Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
    }
}