package com.banco.latinoamericano.modcuentas.infrastructure.output.persistence;

import com.banco.latinoamericano.modcuentas.application.ports.output.MovimientoRepositoryPort;
import com.banco.latinoamericano.modcuentas.domain.model.Cuenta;
import com.banco.latinoamericano.modcuentas.domain.model.Movimiento;
import com.banco.latinoamericano.modcuentas.infrastructure.output.persistence.entity.CuentaEntity;
import com.banco.latinoamericano.modcuentas.infrastructure.output.persistence.entity.MovimientoEntity;
import com.banco.latinoamericano.modcuentas.infrastructure.output.persistence.repository.JpaMovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Marco
 * @date 25/5/2026
 */


@Component
@RequiredArgsConstructor
public class MovimientoPersistenceAdapter implements MovimientoRepositoryPort {

    private final JpaMovimientoRepository repository;

    @Override
    public Movimiento save(Movimiento movimiento) {
        MovimientoEntity entity = toEntity(movimiento);
        return toDomain(repository.save(entity));
    }

    @Override
    public List<Movimiento> findByCuentaAndFechaBetween(String numeroCuenta, LocalDateTime start, LocalDateTime end) {
        return repository.findByCuentaNumeroCuentaAndFechaBetween(numeroCuenta, start, end)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private MovimientoEntity toEntity(Movimiento domain) {
        return MovimientoEntity.builder()
                .id(domain.getId())
                .fecha(domain.getFecha())
                .tipoMovimiento(domain.getTipoMovimiento())
                .valor(domain.getValor())
                .saldo(domain.getSaldo())
                .cuenta(CuentaEntity.builder()
                        .numeroCuenta(domain.getCuenta().getNumeroCuenta())
                        .build())
                .build();
    }

    private Movimiento toDomain(MovimientoEntity entity) {
        return Movimiento.builder()
                .id(entity.getId())
                .fecha(entity.getFecha())
                .tipoMovimiento(entity.getTipoMovimiento())
                .valor(entity.getValor())
                .saldo(entity.getSaldo())
                .cuenta(Cuenta.builder()
                        .numeroCuenta(entity.getCuenta().getNumeroCuenta())
                        .tipoCuenta(entity.getCuenta().getTipoCuenta())
                        .saldoInicial(entity.getCuenta().getSaldoInicial())
                        .estado(entity.getCuenta().getEstado())
                        .clientId(entity.getCuenta().getClientId())
                        .build())
                .build();
    }
}

