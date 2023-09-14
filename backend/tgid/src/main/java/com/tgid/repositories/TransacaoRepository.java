package com.tgid.repositories;

import com.tgid.domain.transacoes.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao,Long> {
}
