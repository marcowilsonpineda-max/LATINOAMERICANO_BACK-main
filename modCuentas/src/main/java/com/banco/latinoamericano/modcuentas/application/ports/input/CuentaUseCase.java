package com.banco.latinoamericano.modcuentas.application.ports.input;

import com.banco.latinoamericano.modcuentas.domain.model.Cuenta;

import java.util.List;

/**
 * @author Marco
 * @date 25/5/2026
 */

public interface CuentaUseCase
{
    Cuenta createCuenta(Cuenta cuenta);
    Cuenta updateCuenta(Cuenta cuenta);
    void deleteCuenta(String numeroCuenta);
    Cuenta getCuentaById(String numeroCuenta);
    List<Cuenta> getAllCuentas();
}
