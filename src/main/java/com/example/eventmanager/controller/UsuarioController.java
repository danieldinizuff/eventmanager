package com.example.eventmanager.controller;


import com.example.eventmanager.model.Favorite;
import com.example.eventmanager.model.Atividade;
import com.example.eventmanager.model.Usuario;
import com.example.eventmanager.service.FavoriteService;
import com.example.eventmanager.service.UsuarioService;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.example.eventmanager.dto.UsuarioDTO;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/usuario")
@Api(value = "API de Gerenciamento de operações relacionadas aos usuarios", description = "Endpoints para Gerenciamento de operações relacionadas aos usuarios")
public class UsuarioController {
    @Autowired
    private final UsuarioService usuarioService;
    @Autowired
    private final FavoriteService favoriteService;
    public UsuarioController(UsuarioService usuarioService, FavoriteService favoriteService) {
        this.usuarioService = usuarioService;
        this.favoriteService = favoriteService;
    }
@PostMapping
    @Operation(summary = "Cadastra um usuário na plataforma.",
            description = "Cadastra um novo usuário na plataforma.")

    public ResponseEntity<String> criarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        
        try {
            Usuario usuario = new Usuario();
            usuario.atualizarUsuario(
                usuarioDTO.getNome(),
                usuarioDTO.getSenha(),
                usuarioDTO.getCpf(),
                usuarioDTO.getEmail(),
                usuarioDTO.getFiliacao());
            Usuario usuarioCadastrado = usuarioService.cadastrarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso! ID: " + usuarioCadastrado.getId());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping
    @ApiOperation(value = "Lista todos os usuarios", notes = "Retorna uma lista de todos os usuarios")
    /*public ResponseEntity<List<Usuario>> createEvent(@RequestBody List<Usuario> usuarios) {
        Usuario createdUsuario = UsuarioService.createUsuario(usuario);
        return new ResponseEntity<>(createdUsuario, HttpStatus.CREATED);}*/
    public ResponseEntity<List<Usuario>> obterTodosUsuarios() {
        List<Usuario> usuarios = usuarioService.obterTodosUsuarios();
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(usuarios);
        }
    }
    

    @GetMapping("/Login")
    @Operation(summary = "Validar login do usuário na plataforma",
            description = "Valida as credenciais do usuário (CPF e senha) para login na plataforma.")
    @Parameter(description = "CPF do usuário", required = true)
    @Parameter(description = "Senha do usuário", required = true)
    public ResponseEntity<String> validarUsuario(@RequestParam String cpf,@RequestParam String senha) {
    boolean resposta = usuarioService.LoginUsuario(cpf, senha);
    if (resposta == true) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("login validado com sucesso");
    }
    else{ return ResponseEntity.status(HttpStatus.NOT_FOUND).body("login invalido");}
        
    }

    @PostMapping("/{usuarioId}/favorite/{atividadeId}")
    @Operation(summary = "Usuário torna uma Atividade favorita")
    public ResponseEntity<String> favoriteAtividade(@PathVariable Long usuarioId, @PathVariable Long atividadeId) {
        try {
            Favorite favorite = favoriteService.addFavorite(usuarioId, atividadeId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Atividade favoritada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao favoritar atividade: " + e.getMessage());
        }
    }

    @DeleteMapping("/{usuarioId}/favorite/{atividadeId}")
    @Operation(summary = "Usuário desfavorita uma Atividade")
    public ResponseEntity<String> unfavoriteAtividade(@PathVariable Long usuarioId, @PathVariable Long atividadeId) {
        try {
            favoriteService.removeFavorite(usuarioId, atividadeId);
            return ResponseEntity.status(HttpStatus.OK).body("Atividade desfavoritada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao desfavoritar atividade: " + e.getMessage());
        }
    }

    @GetMapping("/{usuarioId}/favorites")
    @Operation(summary = "Lista todas as Atividades favoritas")
    public ResponseEntity<?> listFavorites(@PathVariable Long usuarioId) {
        try {
            List<Atividade> favorites = favoriteService.listFavorites(usuarioId);
            if (favorites.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(favorites);
            }
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao listar as atividades favoritas. Por favor, tente novamente mais tarde.");
        }
    }
    @PostMapping("/enviarMensagem")
    public void enviarMensagem(@RequestBody Usuario usuario) {
        if (usuario != null) {
            LocalDateTime horaAtual = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            for (Atividade atividade : usuario.getAtividadesFavoritas()) {
                LocalDateTime horaAtividade = LocalDateTime.parse(atividade.getHorario(), formatter);
                if (horaAtividade.minusHours(1).isBefore(horaAtual)) {
                    enviarEmail(usuario.getEmail(), atividade);
                }
            }
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    private void enviarEmail(String destinatario, Atividade atividade) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(destinatario);
            helper.setSubject("Atividade Favorita em 1 Hora");
            helper.setText("Você tem uma atividade favorita começando em 1 hora:\n" +
                    "Nome: " + atividade.getNome() + "\n" +
                    "Horário: " + atividade.getHorario() + "\n" +
                    "Local: " + atividade.getLocal() + "\n");
            emailSender.send(message);
            System.out.println("Mensagem enviada com sucesso para: " + destinatario);
        } catch (MessagingException e) {
            System.out.println("Erro ao enviar mensagem para: " + destinatario);
            e.printStackTrace();
        }
    }
    }

  
     

    }

   



