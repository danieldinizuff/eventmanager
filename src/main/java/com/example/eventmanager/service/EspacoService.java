package com.example.eventmanager.service;

import com.example.eventmanager.dto.EspacoDTO;
import com.example.eventmanager.model.Espaco;
import com.example.eventmanager.repository.EspacoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspacoService {

    private final EspacoRepository espacoRepository;

    public EspacoService(EspacoRepository espacoRepository) {
        this.espacoRepository = espacoRepository;
    }

    public List<Espaco> getAllEspacos() {
        return espacoRepository.findAll();
    }

    public Optional<Espaco> getEspacoById(Long id) {
        return espacoRepository.findById(id);
    }

    public Espaco saveEspaco(Espaco espaco) {
        return espacoRepository.save(espaco);
    }

    public void deleteEspaco(Long id) {
        espacoRepository.deleteById(id);
    }

    public Optional<Espaco> atualizarEspaco(Long id, EspacoDTO espacoDTO) {
        Optional<Espaco> optionalEspaco = espacoRepository.findById(id);
        if (optionalEspaco.isPresent()) {
            Espaco espaco = optionalEspaco.get();
            espaco.setNome(espacoDTO.getNome());
            espaco.setLocalizacao(espacoDTO.getLocalizacao());
            espaco.setCapacidade(espacoDTO.getCapacidade());
            return Optional.of(espacoRepository.save(espaco));
        } else {
            return Optional.empty();
        }
    }

    public boolean deletarEspaco(Long id) {
        Optional<Espaco> optionalEspaco = espacoRepository.findById(id);
        if (optionalEspaco.isPresent()) {
            espacoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
