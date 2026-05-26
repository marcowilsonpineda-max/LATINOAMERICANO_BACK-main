package com.banco.latinoamericano.modcuentas.application.ports.output;

import com.banco.latinoamericano.modcuentas.domain.model.Cuenta;

import java.util.List;
import java.util.Optional;

/**
 * @author Marco
 * @date 25/5/2026
 */
public interface CuentaRepositoryPort
{
    Cuenta save(Cuenta cuenta);
    Optional<Cuenta> findById(String numeroCuenta);
    List<Cuenta> findAll();
    void deleteById(String numeroCuenta);
}
