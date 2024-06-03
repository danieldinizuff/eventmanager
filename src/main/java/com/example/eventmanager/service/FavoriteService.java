package com.example.eventmanager.service;

import com.example.eventmanager.model.Favorite;
import com.example.eventmanager.model.Usuario;
import com.example.eventmanager.model.Atividade;
import com.example.eventmanager.repository.FavoriteRepository;
import com.example.eventmanager.repository.UsuarioRepository;
import com.example.eventmanager.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AtividadeRepository atividadeRepository;

    // Método para adicionar um favorito
    public Favorite addFavorite(Long usuarioId, Long atividadeId) throws Exception {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new Exception("Usuário não encontrado"));
        Atividade atividade = atividadeRepository.findById(atividadeId)
                .orElseThrow(() -> new Exception("Atividade não encontrada"));

        // Verificar se já foi favoritado
        if (favoriteRepository.findByUsuarioIdAndAtividadeId(usuarioId, atividadeId).isPresent()) {
            throw new Exception("Já favoritado");
        }

        Favorite favorite = new Favorite(usuario, atividade);
        return favoriteRepository.save(favorite);
    }

    // Método para remover um favorito
    public void removeFavorite(Long usuarioId, Long atividadeId) throws Exception {
        Favorite favorite = favoriteRepository.findByUsuarioIdAndAtividadeId(usuarioId, atividadeId)
                .orElseThrow(() -> new Exception("Favorito não encontrado"));
        favoriteRepository.delete(favorite);
    }

    // Método para listar todos os favoritos de um usuário
    public List<Atividade> listFavorites(Long usuarioId) {
        List<Favorite> favoritos = favoriteRepository.findAllByUsuarioId(usuarioId);
        return favoritos.stream().map(Favorite::getAtividade).collect(Collectors.toList());
    }
}
