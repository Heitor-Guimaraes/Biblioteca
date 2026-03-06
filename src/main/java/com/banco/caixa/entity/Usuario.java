package com.banco.caixa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    @OneToOne(mappedBy = "usuario")
    private CarteiraBiblioteca carteiraBiblioteca;

    @OneToMany(mappedBy = "usuario")
    private List<Emprestimo> emprestimos;

}
