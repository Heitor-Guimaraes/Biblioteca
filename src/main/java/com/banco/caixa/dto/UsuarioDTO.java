package com.banco.caixa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private CarteiraBibliotecaDTO carteira;
    private List<EmprestimoDTO> emprestimos;
}
