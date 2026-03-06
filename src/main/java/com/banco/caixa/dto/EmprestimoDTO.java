package com.banco.caixa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmprestimoDTO {
    private Long id;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private Long usuarioId;
}
