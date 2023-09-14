package com.tgid.controller;

import com.tgid.domain.users.Cliente;
import com.tgid.dtos.ClienteDTO;
import com.tgid.dtos.requestDTO.ClienteRequestDTO;
import com.tgid.services.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("cliente")
public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity createCliente(@RequestBody ClienteRequestDTO cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clienteService.criarCliente(cliente));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable Long id){
        return ResponseEntity.ok(this.clienteService.buscarClienteDTOPorId(id));
    }
}
