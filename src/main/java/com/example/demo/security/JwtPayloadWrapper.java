package com.example.demo.security;

import java.util.Map;

public class JwtPayloadWrapper {

    private final Map<String, Object> payload;
    private final String subject;

    public JwtPayloadWrapper(Map<String, Object> payload, String subject) {
        this.payload = payload;
        this.subject = subject;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public String getSubject() {
        return subject;
    }
}
