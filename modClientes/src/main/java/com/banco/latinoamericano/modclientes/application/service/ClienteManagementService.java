package com.banco.latinoamericano.modclientes.application.service;


import com.banco.latinoamericano.modclientes.application.ports.input.ClienteUseCase;
import com.banco.latinoamericano.modclientes.application.ports.output.ClienteRepositoryPort;
import com.banco.latinoamericano.modclientes.domain.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClienteManagementService implements ClienteUseCase {

    private final ClienteRepositoryPort clienteRepository;

    @Override
    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Override
    public Cliente getClienteByExternalId(Long clienteId) {
        return clienteRepository.findByClienteId(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }
}

