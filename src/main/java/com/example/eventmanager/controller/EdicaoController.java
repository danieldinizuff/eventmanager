// src/main/java/com/example/eventmanager/controller/EventController.java
package com.example.eventmanager.controller;

import com.example.eventmanager.model.Edicao;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/eventos")
@Api(value = "Eventos", tags = {"Eventos"})
public class EdicaoController {
    @ApiOperation(value = "Cadastrar uma nova edição de um evento", response = Edicao.class)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Edição cadastrada com sucesso"),
        @ApiResponse(code = 400, message = "Dados inválidos"),
        @ApiResponse(code = 404, message = "Evento não encontrado")
    })
    @PostMapping
    public ResponseEntity<Edicao> cadastrarEdicao(@PathVariable Long eventoId, @RequestBody Edicao edicao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(edicao);
    }
}
