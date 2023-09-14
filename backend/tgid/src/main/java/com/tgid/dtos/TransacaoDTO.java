package com.tgid.dtos;

import com.tgid.domain.transacoes.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Builder
public class TransacaoDTO {
    private Long id;
    private Long clienteId;
    private Long empresaId;
    private BigDecimal valorTaxa;
    private TransactionType tipoTransacao;
    private LocalDateTime timesTamp;

}
