package com.example.demo.security;

import io.jsonwebtoken.*;
import java.util.*;

public class JwtUtil {

    private final String secret = "secret-key";

    public String generateToken(Map<String,Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String generateTokenForUser(com.example.demo.entity.User user) {
        Map<String,Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());
        return generateToken(claims, user.getEmail());
    }

    public Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token) {
        return parseToken(token).getSubject();
    }

    public String extractRole(String token) {
        return (String) parseToken(token).get("role");
    }

    public Long extractUserId(String token) {
        Object id = parseToken(token).get("userId");
        return Long.valueOf(id.toString());
    }

    public boolean isTokenValid(String token, String username) {
        return extractUsername(token).equals(username);
    }
}
