package com.example.eventmanager.service;

import com.example.eventmanager.model.Edicao;
import com.example.eventmanager.model.Event;
import com.example.eventmanager.repository.EdicaoRepository;
import com.example.eventmanager.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdicaoService {

    @Autowired
    private EdicaoRepository edicaoRepository;

    @Autowired
    private EventRepository eventRepository;

    public Edicao salvar(Edicao edicao, Long eventoId) {
        Event evento = eventRepository.findById(eventoId).orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        edicao.setEvento(evento);
        return edicaoRepository.save(edicao);
    }

    public List<Edicao> listarTodos() {
        return edicaoRepository.findAll();
    }

    public Edicao buscarPorId(Long id) {
        return edicaoRepository.findById(id).orElse(null);
    }

    public void deletar(Long id) {
        edicaoRepository.deleteById(id);
    }
}
