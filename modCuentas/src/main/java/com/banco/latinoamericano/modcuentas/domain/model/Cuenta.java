package com.banco.latinoamericano.modcuentas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Marco
 * @date 25/5/2026
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private Boolean estado;
    private String clientId;
}
