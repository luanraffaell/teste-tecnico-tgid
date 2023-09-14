package com.tgid.services;

import com.tgid.domain.users.Empresa;
import com.tgid.repositories.EmpresaRepository;
import com.tgid.services.exceptions.EntidadeNaoEncontradaException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@AllArgsConstructor
@Service
public class EmpresaService {
    private EmpresaRepository empresaRepository;

    public Empresa buscarEmpresaPorId(Long id){
        return this.empresaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Empresa não encontrada!"));
    }
}
