package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private static final String SECRET =
            "thisIsASecretKeyForJwtSigningThatIsAtLeast32BytesLong";

    private static final long EXPIRATION = 1000 * 60 * 60; // 1 hour

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // ===== TEST: t60 =====
    public String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ===== TESTS: t61, t69, t70, t71 =====
    public String generateTokenForUser(Long userId, String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ===== TEST: t62, t63 =====
    public boolean isTokenValid(String token, String expectedEmail) {
        try {
            Claims claims = extractAllClaims(token);
            return claims.getSubject().equals(expectedEmail);
        } catch (Exception e) {
            return false;
        }
    }

    // ===== TEST: t69 =====
    public String extractEmail(String token) {
        return extractAllClaims(token).get("email", String.class);
    }

    // ===== TEST: t70 =====
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // ===== TEST: t71 =====
    public Long extractUserId(String token) {
        Object val = extractAllClaims(token).get("userId");
        if (val instanceof Integer) {
            return ((Integer) val).longValue();
        }
        return (Long) val;
    }

    // ===== TEST: t72 =====
    public Claims parseToken(String token) {
        return extractAllClaims(token);
    }

    // ===== INTERNAL =====
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
}
