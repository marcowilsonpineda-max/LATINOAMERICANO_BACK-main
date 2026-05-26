package com.banco.latinoamericano.modcuentas.application.services;

import com.banco.latinoamericano.modcuentas.application.ports.input.CuentaUseCase;
import com.banco.latinoamericano.modcuentas.application.ports.input.MovimientoUseCase;
import com.banco.latinoamericano.modcuentas.application.ports.output.CuentaRepositoryPort;
import com.banco.latinoamericano.modcuentas.application.ports.output.MovimientoRepositoryPort;
import com.banco.latinoamericano.modcuentas.domain.model.Cuenta;
import com.banco.latinoamericano.modcuentas.domain.model.Movimiento;
import com.banco.latinoamericano.modcuentas.exceptions.SaldoInsuficienteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Marco
 * @date 25/5/2026
 */


@Service
@RequiredArgsConstructor
public class AccountManagementService implements CuentaUseCase, MovimientoUseCase {

    private final CuentaRepositoryPort cuentaRepository;
    private final MovimientoRepositoryPort movimientoRepository;

    @Override
    public Cuenta createCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    public Cuenta updateCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    public void deleteCuenta(String numeroCuenta) {
        cuentaRepository.deleteById(numeroCuenta);
    }

    @Override
    public Cuenta getCuentaById(String numeroCuenta) {
        return cuentaRepository.findById(numeroCuenta)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

    @Override
    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    @Override
    @Transactional
    public Movimiento registerMovement(String numeroCuenta, BigDecimal valor,String TipoMovimiento) {
        Cuenta cuenta = getCuentaById(numeroCuenta);
        BigDecimal nuevoSaldo = cuenta.getSaldoInicial().add(valor);

        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new SaldoInsuficienteException("Saldo no disponible");
        }

        Movimiento mv = Movimiento.builder()
                .cuenta(cuenta)
                .fecha(LocalDateTime.now())
                .valor(valor)
                .saldo(nuevoSaldo)
                .tipoMovimiento(valor.compareTo(BigDecimal.ZERO) > 0 ? "DE" : "RE")
                .build();

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);
        return movimientoRepository.save(mv);
    }

    @Override
    public List<Movimiento> getReport(String numeroCuenta, LocalDateTime start, LocalDateTime end) {
        return movimientoRepository.findByCuentaAndFechaBetween(numeroCuenta, start, end);
    }
}

