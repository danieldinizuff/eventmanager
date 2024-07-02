package com.example.eventmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.eventmanager.model.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
}

