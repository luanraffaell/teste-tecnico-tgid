package com.tgid.domain.transacoes;

import com.tgid.domain.users.Cliente;
import com.tgid.domain.users.Empresa;
import jakarta.persistence.*;
import lombok.*;

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
    private double valorTaxa;

    @Enumerated(EnumType.STRING)
    private TransactionType tipoTransacao;
}
