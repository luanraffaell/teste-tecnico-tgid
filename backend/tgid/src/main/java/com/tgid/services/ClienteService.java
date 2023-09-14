package com.tgid.services;

import com.tgid.domain.users.Cliente;
import com.tgid.dtos.ClienteDTO;
import com.tgid.dtos.requestDTO.ClienteRequestDTO;
import com.tgid.repositories.ClienteRepository;
import com.tgid.services.exceptions.EntidadeNaoEncontradaException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public Cliente buscarClientePorId(Long id){
       return this.clienteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado!"));
    }
    public ClienteDTO buscarClienteDTOPorId(Long id){
        return new ClienteDTO(this.buscarClientePorId(id));
    }
    @Transactional
    public ClienteDTO salvarCliente(Cliente cliente){
        return new ClienteDTO(this.clienteRepository.save(cliente));
    }
    @Transactional
    public ClienteDTO criarCliente(ClienteRequestDTO cliente){
        Cliente novoCliente = Cliente.builder()
                .CPF(cliente.getCpf())
                .email(cliente.getEmail())
                .nome(cliente.getNome())
                .build();
        return this.salvarCliente(novoCliente);
    }
}
