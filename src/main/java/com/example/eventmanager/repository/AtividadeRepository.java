package com.example.eventmanager.repository;

import com.example.eventmanager.model.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
    // Você pode adicionar consultas personalizadas aqui, se necessário
}