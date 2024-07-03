package com.example.eventmanager.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.eventmanager.model.Atividade;
import com.example.eventmanager.model.Atividade.TipoAtividade;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

public class AtividadeDTO {
    private Long id;
    private String nome;
    private String tipo;

    private LocalDate  data;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime  horaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horaFim;

    private String descricao;

    public AtividadeDTO(){
        
    }

    public AtividadeDTO(Long id, String nome, String tipo, LocalDate data, LocalTime horaInicio, LocalTime horaFim, String descricao) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate  getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

}
