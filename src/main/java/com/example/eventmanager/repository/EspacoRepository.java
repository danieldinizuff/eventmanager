package com.example.eventmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.eventmanager.model.Espaco;

public interface EspacoRepository extends JpaRepository<Espaco, Long> {
}

