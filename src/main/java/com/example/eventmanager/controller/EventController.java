package com.example.eventmanager.controller;

import com.example.eventmanager.model.Event;
import com.example.eventmanager.service.EventService;
import java.util.Optional;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eventos")
@Api(value = "Eventos", tags = {"Eventos"})
public class EventController {

    @Autowired
    private EventService eventoService;

    @ApiOperation(value = "Cadastrar um novo evento", response = Event.class)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Evento cadastrado com sucesso"),
        @ApiResponse(code = 400, message = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event evento) {
        Event novoEvento = eventoService.salvar(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEvento);
    }

    @ApiOperation(value = "Atualiza um evento existente")
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvento(@PathVariable Long id, @RequestBody Event eventoDetails) {
        Optional<Event> evento = eventoService.buscarPorId(id);

        if (evento.isPresent()) {
            Event updatedEvento = evento.get();
            updatedEvento.setName(eventoDetails.getName());
            updatedEvento.setDescription(eventoDetails.getDescription());
            updatedEvento.setAcronym(eventoDetails.getAcronym());
            updatedEvento.setPath(eventoDetails.getPath());
            eventoService.salvar(updatedEvento);
            return ResponseEntity.ok(updatedEvento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Deleta um evento por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        eventoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
