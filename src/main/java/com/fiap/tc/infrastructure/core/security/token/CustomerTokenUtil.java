﻿package com.fiap.tc.infrastructure.core.security.token;

import com.fiap.tc.domain.entities.Customer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.stereotype.Component;

import java.util.Date;

import static java.lang.String.format;

@Component
public class CustomerTokenUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expirationTimeInMillis}")
    private long oneHourTtlExpirationInMillis;

    public String generateToken(Customer customer) {
        return Jwts.builder()
                .setSubject(customer.getDocument())
                .claim("id", customer.getId())
                .claim("name", customer.getName())
                .claim("email", customer.getEmail())
                .claim("document", customer.getDocument())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + oneHourTtlExpirationInMillis))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public void validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        } catch (Exception e) {
            throw new UnauthorizedUserException(format("Invalid JWT Token: %s", token));
        }
    }
}