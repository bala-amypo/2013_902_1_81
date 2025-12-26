package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static final String SECRET_KEY = "secretkey123456";

    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String generateTokenForUser(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());
        return generateToken(claims, user.getEmail());
    }

    // ✅ MUST return Jwt<?, ?> so tests can call getPayload()
    public Jwt<?, ?> parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parse(token);
    }

    public String extractUsername(String token) {
        return (String) parseToken(token).getPayload();
    }

    public String extractRole(String token) {
        return (String) ((Claims) parseToken(token).getPayload()).get("role");
    }

    public Long extractUserId(String token) {
        Object id = ((Claims) parseToken(token).getPayload()).get("userId");
        return ((Number) id).longValue();
    }

    public boolean isTokenValid(String token, String username) {
        Claims claims = (Claims) parseToken(token).getPayload();
        return claims.getSubject().equals(username);
    }
}
