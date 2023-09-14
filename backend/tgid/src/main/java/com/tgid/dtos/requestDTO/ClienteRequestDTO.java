package com.tgid.dtos.requestDTO;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteRequestDTO {
    @NotBlank
    private String nome;
    @NotBlank
    private String cpf;
    @NotBlank
    @Email
    private String email;
}
