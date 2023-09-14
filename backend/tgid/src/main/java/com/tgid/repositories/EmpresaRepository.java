package com.tgid.repositories;

import com.tgid.domain.users.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa,Long> {
}
