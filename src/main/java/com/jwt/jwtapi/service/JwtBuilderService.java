package com.jwt.jwtapi.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtBuilderService {
    private String SecretKey = "";
    public JwtBuilderService(){
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey = Base64.getEncoder().encodeToString(keyGen.generateKey().getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
    public String buildToken(UserDetails userDetails) {
        return Jwts.builder()
//.claims()
//.add(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*60*30))
//.and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte[] keybytes = Decoders.BASE64.decode(SecretKey);
        return Keys.hmacShaKeyFor(keybytes);
    }

}
