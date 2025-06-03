package com.example.api_chave_acesso.security;

import com.example.api_chave_acesso.repository.UsuarioRepository;
import com.example.api_chave_acesso.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Serviço que integra o modelo Usuario com o Spring Security carregando o usuário do banco pelo email
@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método que busca o usuário pelo email e retorna os detalhes necessários para autenticação
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return new UsuarioDetails(usuario); // Retorna o objeto UserDetails esperado pelo Spring
    }
}
