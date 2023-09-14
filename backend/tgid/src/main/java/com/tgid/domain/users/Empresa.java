package com.tgid.domain.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name= "tb_empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String CNPJ;
    private BigDecimal saldo;

    public void deposito(BigDecimal quantia){
        this.saldo.add(quantia);
    }
    public void saque(BigDecimal quantia){
        this.saldo.subtract(quantia);
    }
}
