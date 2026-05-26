package com.banco.latinoamericano.modcuentas.infrastructure.input.rest;

import com.banco.latinoamericano.modcuentas.application.ports.input.CuentaUseCase;
import com.banco.latinoamericano.modcuentas.domain.model.Cuenta;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Marco
 * @date 25/5/2026
 */

@RestController

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class AccountController
{


    private final CuentaUseCase cuentaUseCase;

    @GetMapping
    public List<Cuenta> getAll() {
        return cuentaUseCase.getAllCuentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getById(@PathVariable String id) {
        return ResponseEntity.ok(cuentaUseCase.getCuentaById(id));
    }

    @PostMapping
    public ResponseEntity<Cuenta> create(@RequestBody Cuenta cuenta) {
        return new ResponseEntity<>(cuentaUseCase.createCuenta(cuenta), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> update(@PathVariable String id, @RequestBody Cuenta cuenta) {
        cuenta.setNumeroCuenta(id);
        return ResponseEntity.ok(cuentaUseCase.updateCuenta(cuenta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        cuentaUseCase.deleteCuenta(id);
        return ResponseEntity.noContent().build();
    }
}

