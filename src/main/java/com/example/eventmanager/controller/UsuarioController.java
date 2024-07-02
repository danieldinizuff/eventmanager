package com.example.eventmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.eventmanager.model.Usuario;
import com.example.eventmanager.service.UsuarioService;

import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @ApiOperation(value = "Retorna todos os usuários")
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }

    @ApiOperation(value = "Retorna um usuário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Cria um novo usuário")
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @ApiOperation(value = "Atualiza um usuário existente")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        Optional<Usuario> usuario = usuarioService.findById(id);

        if (usuario.isPresent()) {
            Usuario updatedUsuario = usuario.get();
            updatedUsuario.setLogin(usuarioDetails.getLogin());
            updatedUsuario.setEmail(usuarioDetails.getEmail());
            updatedUsuario.setSenha(usuarioDetails.getSenha());
            updatedUsuario.setNomeCompleto(usuarioDetails.getNomeCompleto());
            updatedUsuario.setAfiliacao(usuarioDetails.getAfiliacao());
            usuarioService.save(updatedUsuario);
            return ResponseEntity.ok(updatedUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Deleta um usuário por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

