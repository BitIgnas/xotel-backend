package com.hotelproject.app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    private Key key;

    private final Long JWT_EXPIRATION_MILLI = 900000L;

    @PostConstruct
    public void init() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.ES512);
    }

    public String generateJwtToken(Authentication authentication) {
        User userPrincipal = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setExpiration(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusMillis(JWT_EXPIRATION_MILLI)))
                .signWith(key)
                .addClaims(mapUserAuthorities(userPrincipal.getAuthorities()))
                .compact();
    }

    public Map<String, Object> mapUserAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Map<String, Object> userAuthorities = new HashMap<>();

        authorities.forEach(grantedAuthority -> {
            userAuthorities.put(null, grantedAuthority);
        });

        return userAuthorities;
    }

    public boolean validateJwtToken(String token) {
        Jwts.parser().setSigningKey(key).parseClaimsJws(token);
        return true;
    }

    public String getUsernameFromJwtToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public Long getJwtExpirationMilli() {
        return this.JWT_EXPIRATION_MILLI;
    }
}
