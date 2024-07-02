package com.example.eventmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.eventmanager.model.Espaco;
import com.example.eventmanager.repository.EspacoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EspacoService {

    @Autowired
    private EspacoRepository espacoRepository;

    public List<Espaco> findAll() {
        return espacoRepository.findAll();
    }

    public Optional<Espaco> findById(Long id) {
        return espacoRepository.findById(id);
    }

    public Espaco save(Espaco espaco) {
        return espacoRepository.save(espaco);
    }

    public void deleteById(Long id) {
        espacoRepository.deleteById(id);
    }
}

