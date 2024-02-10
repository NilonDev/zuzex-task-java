package com.nikolay.zuzextask.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImp implements JwtService {
    private final String SECRET_KEY = "UK0XAYG9bK90g3Oz3q3g31Qvdl1HcDIm";
    private final Integer ACTION_TIME = 6000_000; // 10 minutes in milliseconds

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities());

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + ACTION_TIME))
                .claims(claims)
                .signWith(getSignedKey())
                .compact();
    }

    @Override
    public String getUserEmail(String token) {
        return getAllClaims(token).getSubject();
    }

    private Key getSignedKey() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSignedKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

//    private boolean isTokenExpired(String token) {
//        return getExpiration(token).before(new Date(System.currentTimeMillis()));
//    }
//
//    private Date getExpiration(String token) {
//        return getAllClaims(token).getExpiration();
//    }
}
