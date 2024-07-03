package com.example.eventmanager.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

//import java.sql.Time;

@Entity
public class Atividade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String tipo;
    
    @Column(nullable = false)
    private String descricao;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate  data;

    @Column(nullable = false)
    private LocalTime horarioInicial;

    @Column(nullable = false)
    private LocalTime horarioFinal;

    @ManyToOne
    @JoinColumn(name = "edicao_id")
    private Edicao edicao;
    
    @OneToOne(mappedBy = "atividade", cascade = CascadeType.ALL, orphanRemoval = true)
    private Espaco espaco;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate  getData() {
        return data;
    }

    public void setData(LocalDate  data) {
        this.data = data;
    }

    public LocalTime getHorarioInicial() {
        return horarioInicial;
    }

    public void setHorarioInicial(LocalTime horarioInicial) {
        this.horarioInicial = horarioInicial;
    }

    public LocalTime getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(LocalTime horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public Edicao getEdicao() {
        return edicao;
    }

    public void setEdicao(Edicao edicao) {
        this.edicao = edicao;
    }

    public LocalTime getHoraFim() {
        return horarioFinal;
    }

    public void setHoraFim(LocalTime horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public LocalTime getHoraInicio() {
        return horarioInicial;
    }

    public void setHoraInicio(LocalTime horarioInicial) {
        this.horarioInicial = horarioInicial;
    }
    
    // Construtores
    // ...

    public enum TipoAtividade {
        TIPO1,
        TIPO2,
        TIPO3;
        // Adicione mais tipos conforme necessário
    }
}