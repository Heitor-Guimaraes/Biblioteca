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
public class CarteiraBiblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroCarteira;

    @NotNull(message = "Data de emissão é obrigatória")
    private Date dataEmissao;

    private boolean isValid;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    @NotNull(message = "Usuário é obrigatório")
    private Usuario usuario;
}
