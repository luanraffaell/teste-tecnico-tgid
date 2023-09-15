package com.tgid.services;

import com.tgid.domain.transacoes.Transacao;
import com.tgid.domain.transacoes.TransactionType;
import com.tgid.domain.users.Cliente;
import com.tgid.domain.users.Empresa;
import com.tgid.dtos.TransacaoDTO;
import com.tgid.dtos.TransacaoRequestDTO;
import com.tgid.repositories.TransacaoRepository;
import com.tgid.services.factory.EmailNotificacaoService;
import com.tgid.services.factory.TaxaSistema;
import com.tgid.services.factory.TaxaSistemaFactory;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TransacaoService {
    private final EmpresaService empresaService;
    private final ClienteService clienteService;
    private final TransacaoRepository transacaoRepository;

    private final TaxaSistemaFactory taxaSistemaFactory;
    private final WebhookService webhookService;
    private final EmailNotificacaoService emailNotificacaoService;

    public TransacaoDTO criarTransacao(TransacaoRequestDTO transacaoDTO, TransactionType tipoTransacao){
        Empresa empresaAtual = empresaService.buscarEmpresaPorId(transacaoDTO.getEmpresaId());
        Cliente clienteAtual = clienteService.buscarClientePorId((transacaoDTO.getClienteId()));
        TaxaSistema taxaSistema = taxaSistemaFactory.getTaxaSistema(tipoTransacao);
        Transacao.TransacaoBuilder transacao = Transacao.builder()
                .cliente(clienteAtual)
                .empresa(empresaAtual)
                .tipoTransacao(tipoTransacao)
                .valorTaxa(taxaSistema.getValorTaxa())
                .timesTamp(LocalDateTime.now());
        BigDecimal quantia = taxaSistema.aplicarTaxa(transacaoDTO.getValorTransacao());

        saqueDeposito(empresaAtual,quantia,tipoTransacao);
        transacao.valorTransacao(quantia);
        boolean callbackEnviadoParaEmpresa = webhookService.enviarCallback();
        TransacaoDTO novaTransacao = null;
        if (callbackEnviadoParaEmpresa){
            novaTransacao =  salvarTransacao(transacao.build());
            empresaService.salvarEmpresa(empresaAtual);
            clienteService.salvarCliente(clienteAtual);
            emailNotificacaoService.enviarNotificacao(empresaAtual.getEmail(),
                    "Sua transação foi recebida com sucesso. Id da transação:"+novaTransacao.getId());
            emailNotificacaoService.enviarNotificacao(clienteAtual.getEmail(),
                    "Transação realizada com sucesso. Id da transação:"+novaTransacao.getId());
        }



        return novaTransacao;
    }

    @Transactional
    private TransacaoDTO salvarTransacao(Transacao transacao){
        return createDTO(this.transacaoRepository.save(transacao));
    }

    private TransacaoDTO createDTO(Transacao transacao) {
        return TransacaoDTO.builder()
                .id(transacao.getId())
                .timesTamp(transacao.getTimesTamp())
                .tipoTransacao(transacao.getTipoTransacao())
                .clienteId(transacao.getCliente().getId())
                .empresaId(transacao.getEmpresa().getId())
                .valor(transacao.getValorTransacao())
                .valorTaxa(transacao.getValorTaxa())
                .build();
    }

    private void saqueDeposito(Empresa empresa, BigDecimal quantia, TransactionType tipoTransacao){
        if(tipoTransacao.equals(TransactionType.SAQUE)){
            empresa.saque(quantia);
            return;
        }
        empresa.deposito(quantia);
    }

}
