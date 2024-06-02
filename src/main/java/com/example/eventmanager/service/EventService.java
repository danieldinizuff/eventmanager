// src/main/java/com/example/eventmanager/service/EventService.java
package com.example.eventmanager.service;

import com.example.eventmanager.model.Event;
import com.example.eventmanager.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event salvar(Event evento) {
        return eventRepository.save(evento);
    }

    public List<Event> listarTodos() {
        return eventRepository.findAll();
    }

    public Event buscarPorId(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public void deletar(Long id) {
        eventRepository.deleteById(id);
    }
}
