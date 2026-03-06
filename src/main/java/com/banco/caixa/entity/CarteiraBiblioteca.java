package com.banco.caixa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    private Date dataEmissao;
    private boolean isValid;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
