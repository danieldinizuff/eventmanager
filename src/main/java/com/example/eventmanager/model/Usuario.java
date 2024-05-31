// src/main/java/com/example/eventmanager/model/Event.java
package com.example.eventmanager.model;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private byte[]  senha;
    private String cpf;
    private String email;
    private String filiacao;

    // Construtores
    public Usuario() {}

    public Usuario(String name, byte[] senha, String cpf, String email, String filiacao) {
        this.name = name;
        this.senha = senha;
        this.cpf = cpf;
        this.email = email;
        this.filiacao = filiacao;

    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public byte[] getsenha() {
        return senha;
    }

    public void setsenha(byte[] senha) {
        this.senha= senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFiliacao() {
        return filiacao;
    }

    public void setFiliacao(String filiacao) {
        this.filiacao = filiacao;
    }

    public void atualizarUsuario(String nome, String senha, String cpf, String email, String filiacao) {
        if (nome != null && !nome.isEmpty()) {
            this.name = name;
        }
        if (senha != null && !senha.isEmpty()) {
                   try {
             this.senha = (byte[]) senha.getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
        if (cpf != null && !cpf.isEmpty()) {
            this.cpf = cpf;
        }
        if (email != null && !email.isEmpty()) {
            this.email = email;
        }
        if (filiacao != null && !filiacao.isEmpty()) {
            this.filiacao = filiacao;
        }
        
    }


    public boolean validarSenha(String senhaDigitada) {
        try {
            return senhaDigitada.equals(Arrays.toString(this.senha));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
