// src/main/java/com/example/eventmanager/repository/EventRepository.java
package com.example.eventmanager.repository;

import com.example.eventmanager.model.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
}
