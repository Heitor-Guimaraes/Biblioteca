package com.banco.caixa.controller;

import com.banco.caixa.dto.CarteiraBibliotecaDTO;
import com.banco.caixa.entity.CarteiraBiblioteca;
import com.banco.caixa.service.CarteiraBibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carteiras")
public class CarteiraBibliotecaController {

    @Autowired
    private CarteiraBibliotecaService service;

    @GetMapping
    public List<CarteiraBibliotecaDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarteiraBibliotecaDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<CarteiraBibliotecaDTO> create(@PathVariable Long usuarioId, @RequestBody CarteiraBiblioteca carteira) {
        return service.create(usuarioId, carteira)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarteiraBibliotecaDTO> update(@PathVariable Long id, @RequestBody CarteiraBiblioteca details) {
        return service.update(id, details)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
