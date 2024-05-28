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

@RestController
@RequestMapping("/api/events")
@Api(value = "API de Gerenciamento de Eventos", description = "Endpoints para Gerenciamento de Eventos")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping
    @ApiOperation(value = "Lista todos os eventos", notes = "Retorna uma lista de todos os eventos")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }
}
