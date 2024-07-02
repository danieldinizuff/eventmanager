package com.example.eventmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.eventmanager.model.Atividade;
import com.example.eventmanager.service.AtividadeService;

import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @ApiOperation(value = "Retorna todas as atividades")
    @GetMapping
    public List<Atividade> getAllAtividades() {
        return atividadeService.findAll();
    }

    @ApiOperation(value = "Retorna uma atividade por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Atividade> getAtividadeById(@PathVariable Long id) {
        Optional<Atividade> atividade = atividadeService.findById(id);
        return atividade.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Cria uma nova atividade")
    @PostMapping
    public Atividade createAtividade(@RequestBody Atividade atividade) {
        return atividadeService.save(atividade);
    }

    @ApiOperation(value = "Atualiza uma atividade existente")
    @PutMapping("/{id}")
    public ResponseEntity<Atividade> updateAtividade(@PathVariable Long id, @RequestBody Atividade atividadeDetails) {
        Optional<Atividade> atividade = atividadeService.findById(id);

        if (atividade.isPresent()) {
            Atividade updatedAtividade = atividade.get();
            updatedAtividade.setTipo(atividadeDetails.getTipo());
            updatedAtividade.setNome(atividadeDetails.getNome());
            updatedAtividade.setDescricao(atividadeDetails.getDescricao());
            updatedAtividade.setData(atividadeDetails.getData());
            updatedAtividade.setHorarioInicial(atividadeDetails.getHorarioInicial());
            updatedAtividade.setHorarioFinal(atividadeDetails.getHorarioFinal());
            updatedAtividade.setLocal(atividadeDetails.getLocal());
            atividadeService.save(updatedAtividade);
            return ResponseEntity.ok(updatedAtividade);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Deleta uma atividade por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtividade(@PathVariable Long id) {
        atividadeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

