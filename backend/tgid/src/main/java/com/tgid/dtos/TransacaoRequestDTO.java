package com.tgid.dtos;

import java.math.BigDecimal;

public record TransacaoRequestDTO(Long clienteId, Long empresaId, BigDecimal valorTransacao) {
}
