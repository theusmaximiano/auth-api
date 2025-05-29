package com.example.api_chave_acesso.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Chave secreta para assinar o token (deve ser mais complexa e secreta em produção)
    private final String secret = "chave-super-secreta-do-matheus-1234567890";
    private final Key key = Keys.hmacShaKeyFor(secret.getBytes());


    // Tempo de validade do token (exemplo: 10 horas)
    private final long expirationMillis = 36000000;

    // Gera token JWT com o username
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(key)
                .compact();
    }

    // Retorna o username (subject) do token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Verifica se o token é válido (assinatura e data)
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
