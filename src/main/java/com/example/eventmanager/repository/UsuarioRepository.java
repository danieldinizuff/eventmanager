package com.example.eventmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.eventmanager.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
