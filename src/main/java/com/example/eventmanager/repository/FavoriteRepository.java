// src/main/java/com/example/eventmanager/repository/EventRepository.java
package com.example.eventmanager.repository;


import com.example.eventmanager.model.Favorite;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Optional<Favorite> findByUsuarioIdAndAtividadeId(Long usuarioId, Long atividadeId);
    List<Favorite> findAllByUsuarioId(Long usuarioId);

}
