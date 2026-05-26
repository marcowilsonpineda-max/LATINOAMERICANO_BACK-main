package com.banco.latinoamericano.modcuentas.infrastructure.output.persistence.repository;

import com.banco.latinoamericano.modcuentas.infrastructure.output.persistence.entity.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Marco
 * @date 25/5/2026
 */
@Repository
public interface JpaMovimientoRepository extends JpaRepository<MovimientoEntity, Long> {
    List<MovimientoEntity> findByCuentaNumeroCuentaAndFechaBetween(String numeroCuenta, LocalDateTime start, LocalDateTime end);
}
