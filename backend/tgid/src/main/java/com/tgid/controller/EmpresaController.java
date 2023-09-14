package com.tgid.controller;

import com.tgid.domain.users.Empresa;
import com.tgid.dtos.EmpresaDTO;
import com.tgid.dtos.requestDTO.EmpresaRequestDTO;
import com.tgid.services.EmpresaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("empresa")
public class EmpresaController {
    private final EmpresaService empresaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> createEmpresa(@RequestBody EmpresaRequestDTO empresa){
        EmpresaDTO empresaDTO = this.empresaService.criarEmpresa(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO>buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(this.empresaService.buscarEmpresaDTOPorId(id));
    }
}
