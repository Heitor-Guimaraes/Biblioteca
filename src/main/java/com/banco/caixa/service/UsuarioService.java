package com.banco.caixa.service;

import com.banco.caixa.dto.CarteiraBibliotecaDTO;
import com.banco.caixa.dto.EmprestimoDTO;
import com.banco.caixa.dto.UsuarioDTO;
import com.banco.caixa.entity.Usuario;
import com.banco.caixa.repository.CarteiraBibliotecaRepository;
import com.banco.caixa.repository.EmprestimoRepository;
import com.banco.caixa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private CarteiraBibliotecaRepository carteiraRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public List<UsuarioDTO> findAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> findById(Long id) {
        return repository.findById(id).map(this::toDTO);
    }

    @Transactional
    public UsuarioDTO save(Usuario usuario) {
        Usuario savedUsuario = repository.save(usuario);
        
        if (usuario.getCarteiraBiblioteca() != null) {
            usuario.getCarteiraBiblioteca().setUsuario(savedUsuario);
            carteiraRepository.save(usuario.getCarteiraBiblioteca());
        }
        
        if (usuario.getEmprestimos() != null) {
            usuario.getEmprestimos().forEach(e -> {
                e.setUsuario(savedUsuario);
                emprestimoRepository.save(e);
            });
        }
        
        return toDTO(savedUsuario);
    }

    @Transactional
    public Optional<UsuarioDTO> update(Long id, Usuario usuarioDetails) {
        return repository.findById(id).map(usuario -> {
            usuario.setNome(usuarioDetails.getNome());
            usuario.setEmail(usuarioDetails.getEmail());
            
            // Tratamento manual de relações se enviadas no update (simplificado para manter o comportamento anterior)
            return toDTO(repository.save(usuario));
        });
    }

    @Transactional
    public boolean delete(Long id) {
        return repository.findById(id).map(usuario -> {
            if (usuario.getCarteiraBiblioteca() != null) {
                carteiraRepository.delete(usuario.getCarteiraBiblioteca());
            }
            if (usuario.getEmprestimos() != null) {
                emprestimoRepository.deleteAll(usuario.getEmprestimos());
            }
            repository.delete(usuario);
            return true;
        }).orElse(false);
    }

    private UsuarioDTO toDTO(Usuario usuario) {
        CarteiraBibliotecaDTO carteiraDTO = null;
        if (usuario.getCarteiraBiblioteca() != null) {
            carteiraDTO = new CarteiraBibliotecaDTO(
                    usuario.getCarteiraBiblioteca().getNumeroCarteira(),
                    usuario.getCarteiraBiblioteca().getDataEmissao(),
                    usuario.getCarteiraBiblioteca().isValid(),
                    usuario.getId()
            );
        }

        List<EmprestimoDTO> emprestimosDTO = null;
        if (usuario.getEmprestimos() != null) {
            emprestimosDTO = usuario.getEmprestimos().stream()
                    .map(e -> new EmprestimoDTO(
                            e.getId(), 
                            e.getDataEmprestimo(), 
                            e.getDataDevolucao(), 
                            usuario.getId()
                    ))
                    .collect(Collectors.toList());
        }

        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                carteiraDTO,
                emprestimosDTO
        );
    }
}
