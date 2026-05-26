package com.banco.latinoamericano.modcuentas.application.ports.input;

import com.banco.latinoamericano.modcuentas.domain.model.Movimiento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Marco
 * @date 25/5/2026
 * @Maquina -54-54-72
 */

public interface MovimientoUseCase
{
    Movimiento registerMovement(String numeroCuenta, BigDecimal valor, String TipoMovimiento);
    List<Movimiento> getReport(String numeroCuenta, LocalDateTime start, LocalDateTime end);
}
