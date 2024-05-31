package com.example.eventmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eventmanager.model.Atividade;
import com.example.eventmanager.model.Favorite;
import com.example.eventmanager.model.Usuario;
import com.example.eventmanager.repository.AtividadeRepository;
import com.example.eventmanager.repository.FavoriteRepository;
import com.example.eventmanager.repository.UsuarioRepository;

@Service
public class FavoriteService {
@Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AtividadeRepository atividadeRepository;

public Favorite addFavorite(Long usuarioId, Long atividadeId) throws Exception {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new Exception("Usuário não existe"));
        Atividade atividade = atividadeRepository.findById(atividadeId)
                .orElseThrow(() -> new Exception("Atividade não existe"));

        // Verificar se jÃ¡ foi favoritado
        if (favoriteRepository.findByUsuarioIdAndAtividadeId(usuarioId, atividadeId).isPresent()) {
            throw new Exception("Já foi adicionada como favorita.");
        }

        Favorite favorite = new Favorite(usuario, atividade);
        return favoriteRepository.save(favorite);
    }
    public void removeFavorite(Long usuarioId, Long atividadeId) throws Exception {
        Favorite favorite = favoriteRepository.findByUsuarioIdAndAtividadeId(usuarioId, atividadeId)
                .orElseThrow(() -> new Exception("Favorito nÃ£o encontrado"));
        favoriteRepository.delete(favorite);
    }
    public List<Atividade> listFavorites(Long usuarioId) {
        List<Favorite> favoritos = favoriteRepository.findAllByUsuarioId(usuarioId);
        return favoritos.stream().map(Favorite::getAtividade).collect(Collectors.toList());
    }



}
