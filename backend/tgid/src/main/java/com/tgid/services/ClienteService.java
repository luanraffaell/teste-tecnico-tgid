package com.tgid.services;

import com.tgid.domain.users.Cliente;
import com.tgid.repositories.ClienteRepository;
import com.tgid.services.exceptions.EntidadeNaoEncontradaException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@AllArgsConstructor
@Service
public class ClienteService {
    private ClienteRepository clienteRepository;

    public Cliente buscarClientePorId(Long id){
       return this.clienteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado!"));
    }
}
