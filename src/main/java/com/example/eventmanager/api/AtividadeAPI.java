package com.example.eventmanager.api;

import com.example.eventmanager.dto.AtividadeDTO;
import com.example.eventmanager.model.Atividade;
import com.example.eventmanager.service.AtividadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping("/atividades")
@Tag(name = "Atividades", description = "Operações relacionadas a atividades")
public class AtividadeAPI {

    private final AtividadeService atividadeService;

    public AtividadeAPI(AtividadeService atividadeService) {
        this.atividadeService = atividadeService;
    }

    @GetMapping
    @Operation(summary = "Obter todas as atividades")
    public List<Atividade> getAllAtividades() {
        return atividadeService.getAllAtividades();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter uma atividade pelo ID")
    public Atividade getAtividadeById(@PathVariable Long id) {
        return atividadeService.getAtividadeById(id);
    }

    @GetMapping("/simplificada/{id}")
    @Operation(summary = "Obter uma atividade simplificada pelo ID")
    public AtividadeDTO getAtividadeSimplificadaById(@PathVariable Long id) {
        return atividadeService.getAtividadeSimplificadaById(id);
    }

    @PostMapping
    @Operation(summary = "Criar uma nova atividade")
    public ResponseEntity<?> createAtividade(@RequestBody AtividadeDTO atividade) {
        try {
            Atividade savedAtividade = atividadeService.criarAtividade(atividade);
            return ResponseEntity.ok(savedAtividade);
        } catch (Exception e) {
            // Log the exception for internal debugging
            e.printStackTrace(); // Ideally, use a logger

            // Return a more informative error message
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar atividade: " + e.getMessage());
        }
    }
    

    //@PutMapping("/{id}")
    //@Operation(summary = "Atualizar uma atividade pelo ID")
    //public Atividade updateAtividade(@PathVariable Long id, @RequestBody Atividade atividade) {
    //    atividade.setId(id);
    //    return atividadeService.saveAtividade(atividade);
    //}

    //@DeleteMapping("/{id}")
    //@Operation(summary = "Excluir uma atividade pelo ID")
    //public void deleteAtividade(@PathVariable Long id) {
    //    atividadeService.deleteAtividade(id);
    //}

    @PutMapping("/{id}")
    public ResponseEntity<Atividade> atualizarAtividade(@PathVariable Long id, @RequestBody AtividadeDTO atividadeDTO) {
        Optional<Atividade> atividade = atividadeService.atualizarAtividade(id, atividadeDTO);
        return atividade.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtividade(@PathVariable Long id) {
        boolean isRemoved = atividadeService.deletarAtividade(id);
        if (!isRemoved) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
