package com.example.api_chave_acesso.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Chave secreta usada para assinar o token JWT
    private final String secret = "chave-super-secreta-do-matheus-1234567890";
    private final Key key = Keys.hmacShaKeyFor(secret.getBytes());


    // Tempo de expiração do token: 10 horas (em milissegundos)
    private final long expirationMillis = 36000000;

    // Gera um token JWT com base no nome do usuário (email)
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(key)
                .compact();
    }

    // Extrai o username (email) do token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Verifica se o token é válido (assinatura e expiração)
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Token inválido
            return false;
        }
    }
}
