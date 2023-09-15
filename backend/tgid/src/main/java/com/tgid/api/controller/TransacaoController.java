package com.tgid.api.controller;

import com.tgid.domain.transacoes.TransactionType;
import com.tgid.dtos.requestDTO.TransacaoRequestDTO;
import com.tgid.services.TransacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/transacao")
public class TransacaoController {
    private final TransacaoService transacaoService;

    @PostMapping("/saque")
    public ResponseEntity createTransacaoSaque(@RequestBody @Valid TransacaoRequestDTO transacao){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.transacaoService.criarTransacao(transacao, TransactionType.SAQUE));
    }

    @PostMapping("/deposito")
    public ResponseEntity createTransacaoDeposito(@RequestBody @Valid TransacaoRequestDTO transacao){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.transacaoService.criarTransacao(transacao, TransactionType.DEPOSITO));
    }
}
