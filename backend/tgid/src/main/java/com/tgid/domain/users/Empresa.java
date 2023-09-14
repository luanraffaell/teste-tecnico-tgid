package com.tgid.domain.users;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Entity
@Table(name= "tb_empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String CNPJ;
    private BigDecimal saldo;
    private String email;

    public void deposito(BigDecimal quantia){
        this.saldo = this.saldo.add(quantia);
    }
    public void saque(BigDecimal quantia){
       this.saldo = this.saldo.subtract(quantia);
    }
}
