package com.tgid.services;

import com.tgid.domain.transacoes.Transacao;
import com.tgid.domain.transacoes.TransactionType;
import com.tgid.domain.users.Cliente;
import com.tgid.domain.users.Empresa;
import com.tgid.dtos.TransacaoDTO;
import com.tgid.services.factory.TaxaSistemaFactory;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class TransacaoService {
    private EmpresaService empresaService;
    private ClienteService clienteService;
    private TaxaSistemaFactory taxaSistemaFactory;

    public TransacaoDTO criarTransacao(TransacaoDTO transacaoDTO, TransactionType tipoTransacao){
        Empresa empresaAtual = empresaService.buscarEmpresaPorId(transacaoDTO.getEmpresaId());
        Cliente clienteAtual = clienteService.buscarClientePorId((transacaoDTO.getClienteId()));

        Transacao transacao = Transacao.builder()
                .cliente(clienteAtual)
                .empresa(empresaAtual)
                .tipoTransacao(tipoTransacao)
                .valorTaxa(taxaSistemaFactory.getTaxa(tipoTransacao).aplicarTaxa(transacaoDTO.getValorTaxa()))
                .timesTamp(LocalDateTime.now())
                .build();

        return null;
    }

}
