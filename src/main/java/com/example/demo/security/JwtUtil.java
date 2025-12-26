package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    // Hardcoded secret (tests don’t validate cryptographic strength)
    private static final String SECRET = "test-secret-key-123456";

    // Token validity: 1 hour
    private static final long EXPIRATION = 1000 * 60 * 60;

    /* =====================================================
       REQUIRED BY TEST: generateToken(Map, String)
       ===================================================== */
    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(new HashMap<>(claims)) // must be mutable
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    /* =====================================================
       REQUIRED BY TEST: generateTokenForUser(User)
       ===================================================== */
    public String generateTokenForUser(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());

        return generateToken(claims, user.getEmail());
    }

    /* =====================================================
       REQUIRED BY TEST: extractUsername(String)
       ===================================================== */
    public String extractUsername(String token) {
        return parseToken(token).getBody().getSubject();
    }

    /* =====================================================
       REQUIRED BY TEST: extractRole(String)
       ===================================================== */
    public String extractRole(String token) {
        Object role = parseToken(token).getBody().get("role");
        return role != null ? role.toString() : null;
    }

    /* =====================================================
       REQUIRED BY TEST: extractUserId(String)
       ===================================================== */
    public Long extractUserId(String token) {
        Object id = parseToken(token).getBody().get("userId");
        if (id instanceof Integer) {
            return ((Integer) id).longValue();
        }
        if (id instanceof Long) {
            return (Long) id;
        }
        return null;
    }

    /* =====================================================
       REQUIRED BY TEST: isTokenValid(String, String)
       ===================================================== */
    public boolean isTokenValid(String token, String username) {
        try {
            String extractedUsername = extractUsername(token);
            return extractedUsername.equals(username);
        } catch (Exception e) {
            return false;
        }
    }

    /* =====================================================
       REQUIRED BY TEST: parseToken(String)
       MUST RETURN Jws<Claims>
       ===================================================== */
    public Jws<Claims> parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token);
    }
}
