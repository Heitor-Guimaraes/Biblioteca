package com.banco.caixa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarteiraBibliotecaDTO {

    private Long numeroCarteira;
    private Date dataEmissao;
    private boolean isValid;
    private Long usuarioId;
}
