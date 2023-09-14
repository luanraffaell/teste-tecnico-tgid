package com.tgid.dtos;

import com.tgid.domain.users.Cliente;
import lombok.Data;

@Data
public class ClienteDTO{
    private Long id;
    private String nome;
    private String CPF;
    private String email;

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.CPF = cliente.getCPF();
        this.email = cliente.getEmail();
    }
}
