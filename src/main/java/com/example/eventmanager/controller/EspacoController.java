package com.example.eventmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.eventmanager.model.Espaco;
import com.example.eventmanager.service.EspacoService;

import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/espacos")
public class EspacoController {

    @Autowired
    private EspacoService espacoService;

    @ApiOperation(value = "Retorna todos os espaços")
    @GetMapping
    public List<Espaco> getAllEspacos() {
        return espacoService.findAll();
    }

    @ApiOperation(value = "Retorna um espaço por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Espaco> getEspacoById(@PathVariable Long id) {
        Optional<Espaco> espaco = espacoService.findById(id);
        return espaco.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Cria um novo espaço")
    @PostMapping
    public Espaco createEspaco(@RequestBody Espaco espaco) {
        return espacoService.save(espaco);
    }

    @ApiOperation(value = "Atualiza um espaço existente")
    @PutMapping("/{id}")
    public ResponseEntity<Espaco> updateEspaco(@PathVariable Long id, @RequestBody Espaco espacoDetails) {
        Optional<Espaco> espaco = espacoService.findById(id);

        if (espaco.isPresent()) {
            Espaco updatedEspaco = espaco.get();
            updatedEspaco.setNome(espacoDetails.getNome());
            updatedEspaco.setLocalizacao(espacoDetails.getLocalizacao());
            updatedEspaco.setCapacidade(espacoDetails.getCapacidade());
            updatedEspaco.setRecursosDisponiveis(espacoDetails.getRecursosDisponiveis());
            espacoService.save(updatedEspaco);
            return ResponseEntity.ok(updatedEspaco);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Deleta um espaço por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspaco(@PathVariable Long id) {
        espacoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
