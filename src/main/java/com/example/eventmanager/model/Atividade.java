package com.example.eventmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private String nome;
    private String descricao;
    private LocalDateTime data;
    private LocalDateTime horarioInicial;
    private LocalDateTime horarioFinal;
    private String local;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public LocalDateTime getHorarioInicial() {
        return horarioInicial;
    }

    public void setHorarioInicial(LocalDateTime horarioInicial) {
        this.horarioInicial = horarioInicial;
    }

    public LocalDateTime getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(LocalDateTime horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}

