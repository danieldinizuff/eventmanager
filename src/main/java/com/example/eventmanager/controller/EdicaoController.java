package com.example.eventmanager.controller;

import com.example.eventmanager.model.Edicao;
import com.example.eventmanager.service.EdicaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/edicoes")
@Api(value = "Edições de Eventos", tags = {"Edições de Eventos"})
public class EdicaoController {

    @Autowired
    private EdicaoService edicaoService;

    @ApiOperation(value = "Cadastrar uma nova edição de um evento", response = Edicao.class)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Edição cadastrada com sucesso"),
        @ApiResponse(code = 400, message = "Dados inválidos"),
        @ApiResponse(code = 404, message = "Evento não encontrado")
    })
    @PostMapping("/{eventoId}")
    public ResponseEntity<Edicao> cadastrarEdicao(@PathVariable Long eventoId, @RequestBody Edicao edicao) {
        try {
            Edicao novaEdicao = edicaoService.salvar(edicao, eventoId);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaEdicao);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
