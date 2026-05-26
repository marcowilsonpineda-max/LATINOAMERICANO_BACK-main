package com.banco.latinoamericano.modclientes.application.ports.output;


import com.banco.latinoamericano.modclientes.domain.model.Cliente;

public interface EventPublisherPort
{
    void publishClienteCreated(Cliente cliente);
}
