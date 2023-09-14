package com.tgid.domain.transacoes;

import com.tgid.domain.users.Cliente;
import com.tgid.domain.users.Empresa;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Entity
@Table(name= "tb_transacao")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
    private BigDecimal valorTaxa;
    private BigDecimal valorTransacao;

    @Enumerated(EnumType.STRING)
    private TransactionType tipoTransacao;
    private LocalDateTime timesTamp;
}
