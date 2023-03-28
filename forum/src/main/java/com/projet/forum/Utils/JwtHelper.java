package com.projet.forum.Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.projet.forum.Configuration.JwtConfiguration;
import com.projet.forum.Entities.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

@Component //automatic configuration
public class JwtHelper {
    
    private final JwtConfiguration config;
    private final JwtParser parser;
    private final JwtBuilder builder;

    public JwtHelper(JwtConfiguration c){

        this.config = c;

        this.parser = Jwts.parserBuilder().setSigningKey(config.getKey()).build();
        this.builder = Jwts.builder().signWith(config.getKey());
    }

    public String getUsernameFromToken(String token){

        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token){

        return getClaimFromToken(token, Claims::getExpiration);
    }

    public boolean isExpire(String token){

        Date expirationDate = getExpirationDateFromToken(token);

        return expirationDate.after(new Date());
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver){

        final Claims claims = getClaimsFromToken(token);
        
        return claimResolver.apply(claims);
    }

    private Claims getClaimsFromToken(String token){

        return parser.parseClaimsJws(token).getBody();
    }

    public boolean IsTokenValid(String token, UserEntity userDetails){

        String tokenUsername = getUsernameFromToken(token);

        return tokenUsername.equals(userDetails.getUsername()) && !isExpire(token);
    }

    public String generateToken(UserEntity userDetails){

        Map<String, Object> claims = new HashMap<>();

        //customize claims

        return generateToken(claims, userDetails.getUsername());
    }

    private String generateToken(Map<String, Object> claims, String subject){

        SecretKey key = config.getKey();

        return builder.setClaims(claims)
                      .setSubject(subject)
                      .setIssuedAt(new Date())
                      .setExpiration(new Date(System.currentTimeMillis() + config.getExpiration() * 1000L))
                      .compact(); //string generated

        
    }
}
