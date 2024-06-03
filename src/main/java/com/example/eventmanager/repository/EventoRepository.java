package com.example.eventmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eventmanager.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

   
    boolean existsByNome(String nome);
}