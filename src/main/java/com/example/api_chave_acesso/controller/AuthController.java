package com.example.api_chave_acesso.controller;

import com.example.api_chave_acesso.model.Usuario;
import com.example.api_chave_acesso.repository.UsuarioRepository;
import com.example.api_chave_acesso.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Endpoint de login
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> loginData) {
        try {
            String login = loginData.get("login");
            String senha = loginData.get("senha");

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login, senha)
            );

            String token = jwtUtil.generateToken(login);

            return Map.of("token", token);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }
    }

    // (Opcional) Endpoint de cadastro de usuário
    @PostMapping("/register")
    public Map<String, String> register(@Valid @RequestBody Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("Usuário já existe");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);

        return Map.of("mensagem", "Usuário cadastrado com sucesso");
    }
}
