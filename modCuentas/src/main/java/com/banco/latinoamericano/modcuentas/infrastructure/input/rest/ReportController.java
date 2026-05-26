package com.banco.latinoamericano.modcuentas.infrastructure.input.rest;

import com.banco.latinoamericano.modcuentas.application.ports.input.MovimientoUseCase;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Marco
 * @date 25/5/2026
 */
@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor

public class ReportController {

    private final MovimientoUseCase movimientoUseCase;

    @GetMapping
    public List<ReportDTO> getReport(

            @RequestParam("arg0") String numeroCuenta,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") @RequestParam("arg1")  LocalDateTime inicio,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") @RequestParam("arg2")  LocalDateTime fin
    )
    {


        return movimientoUseCase.getReport(numeroCuenta, inicio, fin).stream()
                .map(m -> ReportDTO.builder()
                        .fecha(m.getFecha().toString())
                        .cliente(m.getCuenta().getClientId())
                        .numeroCuenta(m.getCuenta().getNumeroCuenta())
                        .tipo(m.getCuenta().getTipoCuenta())
                        .saldoInicial(m.getSaldo().subtract(m.getValor()))
                        .estado(m.getCuenta().getEstado())
                        .movimiento(m.getValor())
                        .saldoDisponible(m.getSaldo())
                        .build())
                .collect(Collectors.toList());
    }

    @Data
    @Builder
    public static class ReportDTO {
        private String fecha;
        private String cliente;
        private String numeroCuenta;
        private String tipo;
        private java.math.BigDecimal saldoInicial;
        private Boolean estado;
        private java.math.BigDecimal movimiento;
        private java.math.BigDecimal saldoDisponible;
    }
}

