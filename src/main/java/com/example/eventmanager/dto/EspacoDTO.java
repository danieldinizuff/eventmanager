package com.example.eventmanager.dto;

public class EspacoDTO {
    private String nome;
    private String localizacao;
    private int capacidade;

    // Construtores, getters e setters
    public EspacoDTO() {
    }

    public EspacoDTO(String nome, String localizacao, int capacidade) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.capacidade = capacidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}
