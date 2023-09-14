package com.tgid.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class TransacaoRequestDTO {
    @NotNull
    private Long clienteId;
    @NotNull
    private Long empresaId;
    @Positive
    private BigDecimal valorTransacao;
}
