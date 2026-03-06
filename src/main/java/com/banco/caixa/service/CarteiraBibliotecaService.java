package com.banco.caixa.service;

import com.banco.caixa.dto.CarteiraBibliotecaDTO;
import com.banco.caixa.entity.CarteiraBiblioteca;
import com.banco.caixa.repository.CarteiraBibliotecaRepository;
import com.banco.caixa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarteiraBibliotecaService {

    @Autowired
    private CarteiraBibliotecaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<CarteiraBibliotecaDTO> findAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CarteiraBibliotecaDTO> findById(Long id) {
        return repository.findById(id).map(this::toDTO);
    }

    public Optional<CarteiraBibliotecaDTO> create(Long usuarioId, CarteiraBiblioteca carteira) {
        return usuarioRepository.findById(usuarioId).map(usuario -> {
            carteira.setUsuario(usuario);
            return toDTO(repository.save(carteira));
        });
    }

    public Optional<CarteiraBibliotecaDTO> update(Long id, CarteiraBiblioteca details) {
        return repository.findById(id).map(carteira -> {
            carteira.setDataEmissao(details.getDataEmissao());
            carteira.setValid(details.isValid());
            return toDTO(repository.save(carteira));
        });
    }

    public boolean delete(Long id) {
        return repository.findById(id).map(carteira -> {
            repository.delete(carteira);
            return true;
        }).orElse(false);
    }

    private CarteiraBibliotecaDTO toDTO(CarteiraBiblioteca carteira) {
        return new CarteiraBibliotecaDTO(
                carteira.getNumeroCarteira(),
                carteira.getDataEmissao(),
                carteira.isValid(),
                carteira.getUsuario() != null ? carteira.getUsuario().getId() : null
        );
    }
}
