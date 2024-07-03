package com.example.eventmanager.api;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.example.eventmanager.dto.EventoDTO;
import com.example.eventmanager.model.Edicao;
import com.example.eventmanager.model.Evento;
import com.example.eventmanager.service.EventoService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Optional;


@RestController
@RequestMapping("/eventos")
@Tag(name = "Eventos", description = "Operações relacionadas a eventos")
public class EventoAPI {
    private final EventoService eventoService;

 
    public EventoAPI(EventoService eventoService) {
        this.eventoService = eventoService;
    }
    @PostMapping
    @Operation(summary = "Cadastra um novo Evento")
    public ResponseEntity<String> cadastrarEvento(@RequestBody EventoDTO eventoDTO) {
            // Verifica se o evento já existe (pode ser feita uma lógica de verificação aqui)
            if (eventoService.eventoExiste(eventoDTO.getNome())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Evento Já existe!");
                
            } else {
                Evento evento = new Evento(eventoDTO.getNome(),eventoDTO.getSigla(),eventoDTO.getDescricao(), eventoDTO.getCaminho());
                // Se o evento não existe, cadastra o evento
                eventoService.criarEvento(evento);
                return ResponseEntity.status(HttpStatus.CREATED).body("Evento Cadastrado com Sucesso");
            }
        }
    @GetMapping
    @Operation(summary = "Lista todos os eventos cadastrados")
    public ResponseEntity<List<EventoDTO>> listarEventos() {
        List<EventoDTO> eventosDTO = eventoService.listarEventos();

        if (eventosDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(eventosDTO);
    }


    @PutMapping("/{id}")
    public Optional<Evento> atualizarEvento(@PathVariable Long id, @RequestBody EventoDTO eventoDTO) {
        return eventoService.atualizarEvento(id, eventoDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarEvento(@PathVariable Long id) {
        boolean isRemoved = eventoService.deletarEvento(id);
        if (!isRemoved) {
            return ResponseEntity.status(404).body("Evento não encontrado.");
        }
        return ResponseEntity.status(204).body("Evento removido com sucesso.");
    }

}