package com.tgid.dtos.requestDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class EmpresaRequestDTO {
    @NotBlank
    private String nome;
    @NotBlank
    private String cnpj;
    @PositiveOrZero
    private BigDecimal saldo;
    @NotBlank
    @Email
    private String email;
}
