package com.banco.latinoamericano.modcuentas.infrastructure.input.rest;

import com.banco.latinoamericano.modcuentas.application.ports.input.MovimientoUseCase;
import com.banco.latinoamericano.modcuentas.domain.model.Movimiento;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Marco
 * @date 25/5/2026
 */
@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovementController {

    private final MovimientoUseCase movimientoUseCase;

    @PostMapping
    public ResponseEntity<Movimiento> register(@RequestBody MovementRequest request) {
        return new ResponseEntity<>(
                movimientoUseCase.registerMovement(request.getNumeroCuenta(), request.getValor(), request.getTipoMovimiento()),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/reporte")
    public List<Movimiento> getReport(
            @RequestParam("arg0")String numeroCuenta,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") @RequestParam("arg1") LocalDateTime inicio,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") @RequestParam("arg2")  LocalDateTime fin) {
        return movimientoUseCase.getReport(numeroCuenta, inicio, fin);
    }

    /**Pruebas generadas exitosamente
     * Marco Wilson Pineda
     *
     */



    @Data
    public static class MovementRequest {
        private String numeroCuenta;
        private String tipoMovimiento;
        private BigDecimal valor;
    }
}

