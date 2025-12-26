package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * THIS VERSION IS COMPATIBLE WITH YOUR TEST FILE
 * AND YOUR JJWT LIBRARY VERSION
 */
public class JwtUtil {

    private static final String SECRET =
            "THIS_IS_A_VERY_LONG_SECRET_KEY_FOR_JWT_SIGNING_256_BITS_LONG";

    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    /* ---------------------------------------------------
       TOKEN GENERATION
     --------------------------------------------------- */

    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateTokenForUser(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());
        claims.put("userId", user.getId());
        return generateToken(claims, user.getEmail());
    }

    /* ---------------------------------------------------
       PARSING — RETURN WRAPPER (CRITICAL FIX)
     --------------------------------------------------- */

    public ParsedToken parseToken(String token) {
        Jws<Claims> jws = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token);
        return new ParsedToken(jws.getBody());
    }

    /* ---------------------------------------------------
       CLAIM EXTRACTION
     --------------------------------------------------- */

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

    /* ---------------------------------------------------
       VALIDATION
     --------------------------------------------------- */

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

    /* ---------------------------------------------------
       INNER WRAPPER CLASS (TEST NEEDS THIS)
     --------------------------------------------------- */

    public static class ParsedToken {
        private final Claims claims;

        public ParsedToken(Claims claims) {
            this.claims = claims;
        }

        // 🔥 THIS METHOD MAKES THE TEST PASS
        public Claims getPayload() {
            return claims;
        }
    }
}
