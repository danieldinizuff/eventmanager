package com.example.eventmanager.service;

import com.example.eventmanager.dto.AtividadeDTO;
import com.example.eventmanager.model.Atividade;
import com.example.eventmanager.repository.AtividadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;


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
        return new AtividadeDTO(atividade.getId(), atividade.getNome(), atividade.getTipo(), atividade.getData(), atividade.getHoraInicio(), atividade.getHoraFim(), atividade.getDescricao());
    }

    public Atividade saveAtividade(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    public void deleteAtividade(Long id) {
        atividadeRepository.deleteById(id);
    }

    public Atividade criarAtividade(AtividadeDTO atividadeDTO) {
        Atividade atividade = new Atividade();
        atividade.setNome(atividadeDTO.getNome());
        atividade.setTipo(atividadeDTO.getTipo());
        atividade.setData(atividadeDTO.getData());
        atividade.setHoraInicio(atividadeDTO.getHoraInicio());
        atividade.setHoraFim(atividadeDTO.getHoraFim());
        atividade.setDescricao(atividadeDTO.getDescricao());
        return atividadeRepository.save(atividade);
    }

    public Optional<Atividade> atualizarAtividade(Long id, AtividadeDTO atividadeDTO) {
        Optional<Atividade> optionalAtividade = atividadeRepository.findById(id);
        if (optionalAtividade.isPresent()) {
            Atividade atividade = optionalAtividade.get();
            atividade.setNome(atividadeDTO.getNome());
            atividade.setTipo(atividadeDTO.getTipo());
            atividade.setData(atividadeDTO.getData());
            atividade.setHoraInicio(atividadeDTO.getHoraInicio());
            atividade.setHoraFim(atividadeDTO.getHoraFim());
            atividade.setDescricao(atividadeDTO.getDescricao());
            return Optional.of(atividadeRepository.save(atividade));
        } else {
            return Optional.empty();
        }
    }

    public boolean deletarAtividade(Long id) {
        Optional<Atividade> optionalAtividade = atividadeRepository.findById(id);
        if (optionalAtividade.isPresent()) {
            atividadeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
