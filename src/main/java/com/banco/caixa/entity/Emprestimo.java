package com.banco.caixa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Data do empréstimo é obrigatória")
    private Date dataEmprestimo;

    @NotNull(message = "Data de devolução é obrigatória")
    private Date dataDevolucao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @NotNull(message = "Usuário é obrigatório")
    private Usuario usuario;
}
