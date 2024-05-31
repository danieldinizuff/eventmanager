// src/main/java/com/example/eventmanager/repository/UsuarioRepository.java
package com.example.eventmanager.repository;

import com.example.eventmanager.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByCpf(String cpf);
    Usuario findByCpf(String cpf);

}
