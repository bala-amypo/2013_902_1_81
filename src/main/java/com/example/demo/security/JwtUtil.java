package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static final String SECRET = "THIS_IS_A_VERY_SECURE_SECRET_KEY_1234567890";
    private static final long EXPIRATION_MS = 1000 * 60 * 60; // 1 hour

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // ✅ DO NOT MODIFY input map
    public String generateToken(Map<String, Object> claims, String subject) {
        Map<String, Object> safeClaims = new HashMap<>(claims);

        return Jwts.builder()
                .setClaims(safeClaims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateTokenForUser(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());

        return generateToken(claims, user.getEmail());
    }

    public boolean isTokenValid(String token, String username) {
        try {
            String tokenUser = extractUsername(token);
            return tokenUser.equals(username) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return parseToken(token).getBody().getSubject();
    }

    public String extractRole(String token) {
        return (String) parseToken(token).getBody().get("role");
    }

    public Long extractUserId(String token) {
        Object id = parseToken(token).getBody().get("userId");
        return id == null ? null : Long.valueOf(id.toString());
    }

    public Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = parseToken(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}
