package com.banco.caixa.service;

import com.banco.caixa.dto.EmprestimoDTO;
import com.banco.caixa.entity.Emprestimo;
import com.banco.caixa.repository.EmprestimoRepository;
import com.banco.caixa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<EmprestimoDTO> findAll() {
        List<EmprestimoDTO> lista = new ArrayList<>();
        for (Emprestimo e : repository.findAll()) {
            lista.add(toDTO(e));
        }
        return lista;
    }


    public Optional<EmprestimoDTO> findById(Long id) {
        return repository.findById(id).map(this::toDTO);
    }


    public Optional<EmprestimoDTO> create(Long usuarioId, Emprestimo emprestimo) {
        return usuarioRepository.findById(usuarioId).map(usuario -> {
            emprestimo.setUsuario(usuario);
            return toDTO(repository.save(emprestimo));
        });
    }


    public Optional<EmprestimoDTO> update(Long id, Emprestimo details) {
        return repository.findById(id).map(emprestimo -> {
            emprestimo.setDataEmprestimo(details.getDataEmprestimo());
            emprestimo.setDataDevolucao(details.getDataDevolucao());
            return toDTO(repository.save(emprestimo));
        });
    }


    public boolean delete(Long id) {
        return repository.findById(id).map(emprestimo -> {
            repository.delete(emprestimo);
            return true;
        }).orElse(false);
    }


    public List<EmprestimoDTO> findByUsuarioId(Long usuarioId) {
        List<EmprestimoDTO> lista = new ArrayList<>();
        for (Emprestimo e : repository.findAll()) {
            if (e.getUsuario() != null && e.getUsuario().getId().equals(usuarioId)) {
                lista.add(toDTO(e));
            }
        }
        return lista;
    }

    private EmprestimoDTO toDTO(Emprestimo emprestimo) {
        return new EmprestimoDTO(
                emprestimo.getId(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucao(),
                emprestimo.getUsuario() != null ? emprestimo.getUsuario().getId() : null
        );
    }
}