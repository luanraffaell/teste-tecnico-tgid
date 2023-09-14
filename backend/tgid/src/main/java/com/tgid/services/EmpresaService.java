package com.tgid.services;

import com.tgid.domain.users.Empresa;
import com.tgid.dtos.EmpresaDTO;
import com.tgid.dtos.requestDTO.EmpresaRequestDTO;
import com.tgid.repositories.EmpresaRepository;
import com.tgid.services.exceptions.EntidadeNaoEncontradaException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    public Empresa buscarEmpresaPorId(Long id){
        return this.empresaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Empresa não encontrada!"));
    }
    @Transactional
    public Empresa salvarEmpresa(Empresa empresa){
        return this.empresaRepository.save(empresa);
    }
    @Transactional
    public EmpresaDTO criarEmpresa(EmpresaRequestDTO empresaRequest){
        Empresa empresa = Empresa.builder()
                .email(empresaRequest.getEmail())
                .CNPJ(empresaRequest.getCnpj())
                .nome(empresaRequest.getNome())
                .saldo(empresaRequest.getSaldo())
                .build();
        return new EmpresaDTO(this.salvarEmpresa(empresa));
    }

}
