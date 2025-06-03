package com.example.api_chave_acesso.security;

import com.example.api_chave_acesso.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

// Classe que implementa UserDetails para integrar o modelo Usuario com o Spring Security
public class UsuarioDetails implements UserDetails {

    private final Usuario usuario;

    public UsuarioDetails(Usuario usuario) {
        this.usuario = usuario;
    }

    // Autoridades/perfis do usuário (roles). Aqui retorna vazio, mas pode ser estendido
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Sem roles por enquanto
    }

    // Retorna a senha criptografada do usuário
    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    // Retorna o username (email no caso) do usuário
    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    // Indica se a conta do usuário está expirada (sempre true para ativo)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Indica se a conta está bloqueada (sempre true para desbloqueado)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Indica se as credenciais (senha) estão expiradas (sempre true)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Indica se o usuário está habilitado (sempre true)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
