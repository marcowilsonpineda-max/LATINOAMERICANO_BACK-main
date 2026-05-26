package com.banco.latinoamericano.modcuentas.application.ports.output;

import com.banco.latinoamericano.modcuentas.domain.model.Movimiento;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Marco
 * @date 25/5/2026
 */
public interface MovimientoRepositoryPort
{
    Movimiento save(Movimiento movimiento);
    List<Movimiento> findByCuentaAndFechaBetween(String numeroCuenta, LocalDateTime start, LocalDateTime end);
}
