package com.banco.latinoamericano.modcuentas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Marco
 * @date 25/5/2026
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movimiento {
    private Long id;
    private LocalDateTime fecha;
    private String tipoMovimiento;
    private BigDecimal valor;
    private BigDecimal saldo;
    private Cuenta cuenta;
}
