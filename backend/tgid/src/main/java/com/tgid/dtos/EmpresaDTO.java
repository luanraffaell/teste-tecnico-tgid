package com.tgid.dtos;

import com.tgid.domain.users.Empresa;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmpresaDTO {
    private Long id;
    private String nome;
    private String cnpj;
    private BigDecimal saldo;
    private String email;

    public EmpresaDTO(Empresa empresa) {
        this.id = empresa.getId();
        this.nome = empresa.getNome();
        this.cnpj = empresa.getCNPJ();
        this.saldo = empresa.getSaldo();
        this.email = empresa.getEmail();
    }
}
