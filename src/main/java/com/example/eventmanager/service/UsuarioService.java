// src/main/java/com/example/eventmanager/service/EventService.java
package com.example.eventmanager.service;

import com.example.eventmanager.model.Usuario;
import com.example.eventmanager.repository.UsuarioRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    private final UsuarioRepository usuarioRepository;
    
    public Usuario cadastrarUsuario(Usuario usuario) {

        if (usuarioRepository.existsByCpf(usuario.getCpf())) {
            throw new RuntimeException("CPF já está em uso!");
        }
        
        return usuarioRepository.save(usuario);
    }

    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
    public List<Usuario> obterTodosUsuarios() {
        return usuarioRepository.findAll();
    }
    public boolean usuarioExiste(String cpf) {
        return usuarioRepository.existsByCpf(cpf);
    }

    public Boolean LoginUsuario(String cpf,String senha) {
        
        if (usuarioExiste(cpf) &&usuarioRepository.findByCpf(cpf).validarSenha(senha)) {
                return true;
            }
        else {
                return false;}
    }


}
