// src/main/java/com/example/eventmanager/controller/EventController.java
package com.example.eventmanager.controller;

import com.example.eventmanager.model.Event;
import com.example.eventmanager.service.EventService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/eventos")
@Api(value = "Eventos", tags = {"Eventos"})
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping
    @ApiOperation(value = "Cadastrar um novo evento", response = Event.class)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Evento cadastrado com sucesso"),
        @ApiResponse(code = 400, message = "Dados inválidos")
    })
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }
}
