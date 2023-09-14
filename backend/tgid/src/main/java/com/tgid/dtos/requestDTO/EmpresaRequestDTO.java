package com.tgid.dtos.requestDTO;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class EmpresaRequestDTO {

    private String nome;
    private String cnpj;
    private BigDecimal saldo;
    private String email;
}
