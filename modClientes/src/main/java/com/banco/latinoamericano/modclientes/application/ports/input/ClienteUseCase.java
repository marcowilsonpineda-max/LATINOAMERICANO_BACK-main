package com.banco.latinoamericano.modclientes.application.ports.input;

import com.banco.latinoamericano.modclientes.domain.model.Cliente;

import java.util.List;

public interface ClienteUseCase
{
    Cliente createCliente(Cliente cliente);
    Cliente updateCliente(Cliente cliente);
    void deleteCliente(Long id);
    Cliente getClienteById(Long id);
    Cliente getClienteByExternalId(Long clienteId);
    List<Cliente> getAllClientes();
}
