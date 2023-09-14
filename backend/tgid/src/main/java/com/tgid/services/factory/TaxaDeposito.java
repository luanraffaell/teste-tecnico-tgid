package com.tgid.services.factory;

import com.tgid.domain.transacoes.TransactionType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TaxaDeposito implements TaxaSistema {
    private final BigDecimal TAXA = BigDecimal.valueOf(5.0);
    private final TransactionType TYPE = TransactionType.DEPOSITO;
    @Override
    public BigDecimal getValorTaxa() {
        return this.TAXA;
    }

    @Override
    public BigDecimal aplicarTaxa(BigDecimal valor) {
        return valor.subtract(TAXA);
    }

    @Override
    public TransactionType getTransactionName() {
        return this.TYPE;
    }
}
