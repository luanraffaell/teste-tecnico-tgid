package com.tgid.services.factory;

import com.tgid.domain.transacoes.TransactionType;

import java.math.BigDecimal;

public interface TaxaSistema {
    BigDecimal getValorTaxa();
    BigDecimal aplicarTaxa(BigDecimal valor);
    TransactionType getTransactionName();
}
