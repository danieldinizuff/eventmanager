package com.example.eventmanager.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.eventmanager.dto.EdicaoDTO;
import com.example.eventmanager.model.Edicao;
import com.example.eventmanager.model.Evento;
import com.example.eventmanager.repository.EdicaoRepository;

import jakarta.validation.ConstraintViolationException;

import java.util.List;

import java.util.Optional;


@Service
public class EdicaoService {

    private final EdicaoRepository edicaoRepository;
    public EdicaoService(EdicaoRepository edicaoRepository) {
        this.edicaoRepository = edicaoRepository;
    }

    public Edicao salvarEdicao(Edicao edicao) {
        try {
            return edicaoRepository.save(edicao);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                throw new RuntimeException("Erro: A combinação de ano e evento já existe.");
            }
            throw e;
        }
    }

    public Edicao buscarPorId(Long id) {
        return edicaoRepository.findById(id).orElse(null);
    }

    public List<Edicao> listarTodasEdicoes() {
        return edicaoRepository.findAll();
    }

    public void deletarEdicao2(Long id) {
        edicaoRepository.deleteById(id);
    }
    public boolean verificarExistenciaEdicaoPorAnoEEvento(Evento evento, int ano) {
        return edicaoRepository.existsByEventoAndAno(evento, ano);
    }
    public List<Edicao> listarEdicoesDeEvento(Evento evento) {
        return edicaoRepository.findByEvento(evento);
    }



    public Optional<Edicao> atualizarEdicao(Long id, EdicaoDTO edicaoDTO) {
        Optional<Edicao> optionalEdicao = edicaoRepository.findById(id);
        if (optionalEdicao.isPresent()) {
            Edicao edicao = optionalEdicao.get();
            edicao.setNumero(edicaoDTO.getNumero());
            edicao.setAno(edicaoDTO.getAno());
            edicao.setDataInicial(edicaoDTO.getDataInicial());
            edicao.setDataFinal(edicaoDTO.getDataFinal());
            edicao.setCidade(edicaoDTO.getCidade());
            edicao.setChamadaTrabalhos(edicaoDTO.getChamadaTrabalhos());
            edicao.setDataLimiteSubmissao(edicaoDTO.getDataLimiteSubmissao());
            edicao.setDataDivulgacaoTrabalhos(edicaoDTO.getDataDivulgacaoTrabalhos());
            edicao.setDataEntregaFinal(edicaoDTO.getDataEntregaFinal());
            edicao.setInformacoesInscricoes(edicaoDTO.getInformacoesInscricoes());
            edicao.setPrecoLote1(edicaoDTO.getPrecoLote1());
            edicao.setPrecoLote2(edicaoDTO.getPrecoLote2());
            edicao.setLinkInscricoes(edicaoDTO.getLinkInscricoes());
            return Optional.of(edicaoRepository.save(edicao));
        } else {
            return Optional.empty();
        }
    }


    public boolean deletarEdicao(Long id) {
        Optional<Edicao> optionalEdicao = edicaoRepository.findById(id);
        if (optionalEdicao.isPresent()) {
            edicaoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }



}
