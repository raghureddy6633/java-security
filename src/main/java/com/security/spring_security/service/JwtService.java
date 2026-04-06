package com.security.spring_security.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.security.secretkey}")
    private String serect_key = "faskja;ljlsj";

    @Value("${jwt.security.expirytime}")
    private Long expirationTime = System.currentTimeMillis();


    // extract the username from the token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // validation token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignKey()).build().parse(token);
            return true;
        } catch (MalformedJwtException e){
            System.out.println("Invalid JWT token: {}" + e.getMessage());
        } catch (ExpiredJwtException e){
            System.out.println("JWT Token is expired: {}" + e.getMessage());
        }catch (UnsupportedJwtException e){
            System.out.println("JWt token is unsupported: {}" + e.getMessage());
        }catch (Exception e){
            System.out.println("Invalid JWT Token" + e.getMessage());
        }
        return false;
    }

    //generate Token
    public String generateJwtToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("Client", "JavaExpress");

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 300000))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(serect_key));
    }
}
