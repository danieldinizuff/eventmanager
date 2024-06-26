package com.example.eventmanager.model;

import java.io.Serializable;

import com.example.eventmanager.security.*;
import java.util.Arrays;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "senha")
    private byte[] senha;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "email")
    private String email;

    @Column(name = "filiacao")
    private String filiacao;


    // Construtor vazio
    public Usuario() {
    }

    // Construtor com campos obrigatórios
    public Usuario(String nome, String senha, String cpf, String email, String filiacao) {
        this.nome = nome;
        try {
            this.senha = AESCrypt.encrypt(senha);
        } catch (Exception e) {
            System.out.println("criptografia n ocorreu");
            e.printStackTrace();
        }
        this.cpf = cpf;
        this.email = email;
        this.filiacao = filiacao;
    }
    public Usuario(Usuario usuario) {
        this(usuario.getNome(), usuario.getSenha(), usuario.getCpf(), usuario.getEmail(), usuario.getFiliacao());
        this.id = usuario.getId();
    }
    // Getters e Setters

    public Long getId() {
        return id;
    }
       public void setId(long id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        try {
            return AESCrypt.decrypt(senha);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public void setSenha(String senha) {
       
        try {
             this.senha =AESCrypt.encrypt(senha);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
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
            this.nome = nome;
        }
        if (senha != null && !senha.isEmpty()) {
                   try {
             this.senha =AESCrypt.encrypt(senha);
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

    // Método para validar a senha
    public boolean validarSenha(String senhaDigitada) {
        try {
             System.out.println("senha armazenada: "+Arrays.toString(this.senha));
             System.out.println("senha digitada:   "+Arrays.toString(AESCrypt.encrypt(senhaDigitada)));
             System.out.println("resultado: "+Arrays.toString(AESCrypt.encrypt(senhaDigitada)).equals(Arrays.toString(this.senha)));
            return Arrays.toString(AESCrypt.encrypt(senhaDigitada)).equals(Arrays.toString(this.senha));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}