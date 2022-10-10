package com.qds.backend.evaluacion.springrestqdsnotas.security.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_TOKEN_VALIDITY = 100 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    public String obtenerUsuarioDeToken(String token) {
        return obtenerClainDeToken(token, Claims::getSubject);
    }

    public Date obtenerFechaVencimientoDeToken(String token) {
        return obtenerClainDeToken(token, Claims::getExpiration);
    }

    public <T> T obtenerClainDeToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = obtenerClaimsDeToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims obtenerClaimsDeToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean tokenExpirado(String token) {
        final Date expiration = obtenerFechaVencimientoDeToken(token);
        return expiration.before(new Date());
    }

    public String generarToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return realizarGeneracionToken(claims, userDetails.getUsername());
    }

    private String realizarGeneracionToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validarToken(String token, UserDetails userDetails) {
        final String username = obtenerUsuarioDeToken(token);
        return (username.equals(userDetails.getUsername()) && !tokenExpirado(token));
    }


}

