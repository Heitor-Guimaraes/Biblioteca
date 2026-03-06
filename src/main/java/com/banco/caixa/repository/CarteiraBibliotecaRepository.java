package com.banco.caixa.repository;

import com.banco.caixa.entity.CarteiraBiblioteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraBibliotecaRepository extends JpaRepository<CarteiraBiblioteca, Long> {
}
