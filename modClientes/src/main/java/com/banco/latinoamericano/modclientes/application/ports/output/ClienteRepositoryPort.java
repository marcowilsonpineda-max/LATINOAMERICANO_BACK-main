package com.banco.latinoamericano.modclientes.application.ports.output;

import com.banco.latinoamericano.modclientes.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepositoryPort
{
    Cliente save(Cliente cliente);
    Optional<Cliente> findById(Long id);
    Optional<Cliente> findByClienteId(Long clienteId);
    List<Cliente> findAll();
    void deleteById(Long id);
}
