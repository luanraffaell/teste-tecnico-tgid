package com.tgid.services.factory;

import com.tgid.domain.transacoes.TransactionType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class TaxaSistemaFactory {
    private Map<String, TaxaSistema> taxasSistema = new HashMap<>();

    public TaxaSistemaFactory(Set<TaxaSistema> taxas) {
        createStrategy(taxas);
    }

    private void createStrategy(Set<TaxaSistema> taxas) {
        taxas.forEach(t -> this.taxasSistema.put(t.getTransactionName().name(),t));
    }

    public TaxaSistema getTaxaSistema(TransactionType type){
        return this.taxasSistema.get(type.name());
    }
}
