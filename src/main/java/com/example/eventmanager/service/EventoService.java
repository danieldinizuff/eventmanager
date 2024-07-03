package com.example.eventmanager.service;

import java.util.Optional;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.example.eventmanager.dto.EventoDTO;
import com.example.eventmanager.model.Evento;
import com.example.eventmanager.repository.EventoRepository;



@Service
public class EventoService {
    
    private final EventoRepository eventoRepository;

    
    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public Evento criarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public boolean eventoExiste(String nome) {
        return eventoRepository.existsByNome(nome);
    }

     public Optional<Evento> getEvento(int id){
        return eventoRepository.findById((long)id);
    }
     public List<EventoDTO> listarEventos() {
        List<Evento> eventos = eventoRepository.findAll();
        return eventos.stream()
                .map(evento -> new EventoDTO(evento.getNome(), evento.getSigla(), evento.getDescricao(), evento.getCaminho()))
                .collect(Collectors.toList());
    }


    public Optional<Evento> atualizarEvento(Long id, EventoDTO eventoDTO) {
        Optional<Evento> optionalEvento = eventoRepository.findById(id);
        if (optionalEvento.isPresent()) {
            Evento evento = optionalEvento.get();
            evento.setNome(eventoDTO.getNome());
            evento.setSigla(eventoDTO.getSigla());
            evento.setDescricao(eventoDTO.getDescricao());
            evento.setCaminho(eventoDTO.getCaminho());
            return Optional.of(eventoRepository.save(evento));
        } else {
            return Optional.empty();
        }
    }


    public boolean deletarEvento(Long id) {
        Optional<Evento> optionalEvento = eventoRepository.findById(id);
        if (optionalEvento.isPresent()) {
            eventoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


}
