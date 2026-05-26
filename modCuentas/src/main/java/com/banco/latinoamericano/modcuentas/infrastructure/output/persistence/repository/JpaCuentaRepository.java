package com.banco.latinoamericano.modcuentas.infrastructure.output.persistence.repository;

import com.banco.latinoamericano.modcuentas.infrastructure.output.persistence.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Marco
 * @date 25/5/2026
 */
@Repository
public interface JpaCuentaRepository extends JpaRepository<CuentaEntity, String> {
}
