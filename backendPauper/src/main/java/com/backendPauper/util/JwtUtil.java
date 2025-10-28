package com.backendPauper.util;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final Key key;
   	private static final long EXPIRATION_TIME = 1000 * 60 * 60; 
    
    public JwtUtil(@Value("${app.jwt.secret}") String secret) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
	}
    

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("PauperSevilla")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
