package com.eventify.PaymentService.util;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Secret key for signing the JWT
    private final String SECRET_KEY = "qR7WmcTJvYskp6Kw9AEG4dLzBnXh2U3R";

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // Valid for 10 hours
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("authorities", "ROLE_" + role)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token, String email) {
        final String extractedEmail = extractAllClaims(token).getSubject();
        return (extractedEmail.equals(email) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
