package com.example.eventmanager.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.eventmanager.model.Edicao;
import com.example.eventmanager.model.Evento;

public interface EdicaoRepository extends JpaRepository<Edicao, Long> {

    List<Edicao> findByEvento(Evento evento);
    boolean existsByEventoAndAno(Evento evento, int ano);
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE edicao SET organizador_id = :idOrganizador WHERE id = :idEdicao")
    @Transactional
    void AtribuirEdicao(@Param("idOrganizador") Long idOrganizador, @Param("idEdicao")long idEdicao);
    
}