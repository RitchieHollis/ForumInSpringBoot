package com.projet.forum.Configuration;

import javax.crypto.SecretKey;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtConfiguration {
    
    private String secret;

    private int expiration = 24 * 3600;

    private SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
}
