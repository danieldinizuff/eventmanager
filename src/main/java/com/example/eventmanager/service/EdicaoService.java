package com.example.eventmanager.service;

import com.example.eventmanager.model.Edicao;
import com.example.eventmanager.repository.EdicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdicaoService {

    @Autowired
    private EdicaoRepository edicaoRepository;

    public Edicao salvar(Edicao edicao) {
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
