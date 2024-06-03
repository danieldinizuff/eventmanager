package com.example.eventmanager.service;

import com.example.eventmanager.dto.AtividadeDTO;
import com.example.eventmanager.model.Atividade;
import com.example.eventmanager.repository.AtividadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AtividadeService {

    private final AtividadeRepository atividadeRepository;
    public AtividadeService(AtividadeRepository atividadeRepository) {
        this.atividadeRepository = atividadeRepository;
    }

    public List<Atividade> getAllAtividades() {
        return atividadeRepository.findAll();
    }

    public Atividade getAtividadeById(Long id) {
        return atividadeRepository.findById(id).orElse(null);
    }

    public AtividadeDTO getAtividadeSimplificadaById(Long id){
        Atividade atividade = atividadeRepository.findById(id).orElse(null);
        return new AtividadeDTO(atividade);
    }

    public Atividade saveAtividade(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    public void deleteAtividade(Long id) {
        atividadeRepository.deleteById(id);
    }
}
