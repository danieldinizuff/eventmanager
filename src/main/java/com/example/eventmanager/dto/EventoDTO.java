package com.example.eventmanager.dto;

public class EventoDTO {
    private String nome;
    private String sigla;
    private String descricao;
    private String caminho;

    public EventoDTO() {
        // Construtor vazio necessário para o Jackson (biblioteca de serialização/desserialização JSON)
    }

    public EventoDTO(String nome, String sigla, String descricao, String caminho) {
        this.nome = nome;
        this.sigla = sigla;
        this.descricao = descricao;
        this.caminho = caminho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCaminho(){
        return caminho;
    }

    public void setCaminho(String caminho){
        this.caminho = caminho;
    }

    @Override
    public String toString() {
        return "EventoDTO{" +
                "nome='" + nome + '\'' +
                ", sigla='" + sigla + '\'' +
                ", descricao='" + descricao + '\'' +
                ", caminho='" + caminho + '\'' +
                '}';
    }
}
