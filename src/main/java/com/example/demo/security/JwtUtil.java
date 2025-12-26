package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * TEST-COMPATIBLE JWT UTIL
 * Works with OLD JJWT + TEST EXPECTATIONS
 */
public class JwtUtil {

    private static final String SECRET = "TEST_SECRET_KEY_123456789";
    private static final long EXPIRATION = 1000 * 60 * 60; // 1 hour

    /* ================= TOKEN CREATION ================= */

    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String generateTokenForUser(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());
        claims.put("userId", user.getId());
        return generateToken(claims, user.getEmail());
    }

    /* ================= TOKEN PARSING ================= */

    public ParsedToken parseToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();

        return new ParsedToken(claims);
    }

    /* ================= CLAIM EXTRACTION ================= */

    public String extractUsername(String token) {
        return parseToken(token).getPayload().getSubject();
    }

    public String extractRole(String token) {
        return (String) parseToken(token).getPayload().get("role");
    }

    public Long extractUserId(String token) {
        Object id = parseToken(token).getPayload().get("userId");
        if (id instanceof Integer) {
            return ((Integer) id).longValue();
        }
        return (Long) id;
    }

    /* ================= VALIDATION ================= */

    public boolean isTokenValid(String token, String username) {
        try {
            return extractUsername(token).equals(username)
                    && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return parseToken(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }

    /* ================= WRAPPER CLASS (KEY FIX) ================= */

    /**
     * This wrapper exists ONLY to satisfy:
     * parseToken(token).getPayload()
     */
    public static class ParsedToken {

        private final Claims claims;

        public ParsedToken(Claims claims) {
            this.claims = claims;
        }

        public Claims getPayload() {
            return claims;
        }
    }
}
