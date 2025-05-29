package com.example.api_chave_acesso.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // Email será usado como login
    private String email;

    private String senha;

    private String role; // Ex: ROLE_ADMIN, ROLE_USER
}
