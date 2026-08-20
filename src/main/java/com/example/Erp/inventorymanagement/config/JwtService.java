package com.example.Erp.inventorymanagement.config;

import com.example.Erp.inventorymanagement.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey secretKey;
    private final long expiration;

    public JwtService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expiration
    ) {

        this.secretKey = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secret)
        );

        this.expiration = expiration;
    }


    public String generateToken(User user) {

        String role = "USER";

        if (user.getRole() != null &&
                user.getRole().getRoleName() != null) {

            role = user.getRole().getRoleName();
        }

        Instant now = Instant.now();

        return Jwts.builder()
                .subject(user.getUsername())

                .claim("userId", user.getUserId())
                .claim("email", user.getEmail())
                .claim("role", role)

                .issuedAt(Date.from(now))

                .expiration(
                        Date.from(
                                now.plusMillis(expiration)
                        )
                )

                .signWith(secretKey)

                .compact();
    }


    public String extractUsername(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }


    public boolean isTokenValid(String token) {

        final String username = extractUsername(token);
//        return username.equals(userDetails.getUsername());
        return !username.equals("");

//        try {
//
//            Claims claims = Jwts.parser()
//                    .verifyWith(secretKey)
//                    .build()
//                    .parseSignedClaims(token)
//                    .getPayload();
//
//            return claims.getExpiration()
//                    .after(new Date());
//
//        } catch (Exception e) {
//
//            return false;
//
//        }
    }
}