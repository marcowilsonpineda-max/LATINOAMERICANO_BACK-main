package com.banco.latinoamericano.modclientes.infraestructure.adapters.output.persistence;

import com.banco.latinoamericano.modclientes.application.ports.output.ClienteRepositoryPort;
import com.banco.latinoamericano.modclientes.domain.model.Cliente;
import com.banco.latinoamericano.modclientes.infraestructure.adapters.output.persistence.entity.ClienteEntity;

import com.banco.latinoamericano.modclientes.infraestructure.adapters.output.persistence.repository.JpaClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClientePersistenceAdapter implements ClienteRepositoryPort {

    private final JpaClienteRepository repository;

    @Override
    public Cliente save(Cliente cliente) {
        ClienteEntity entity = toEntity(cliente);
        return toDomain(repository.save(entity));
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Cliente> findByClienteId(Long clienteId) {
        return repository.findByClienteId(clienteId).map(this::toDomain);
    }

    @Override
    public List<Cliente> findAll() {
        return repository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private ClienteEntity toEntity(Cliente domain) {
        return ClienteEntity.builder()
                .id(domain.getId())
                .nombre(domain.getNombre())
                .genero(domain.getGenero())
                .edad(domain.getEdad())
                .identificacion(domain.getIdentificacion())
                .direccion(domain.getDireccion())
                .telefono(domain.getTelefono())
                .clienteId(domain.getClienteId())
                .contrasena(domain.getContrasena())
                .estado(domain.getEstado())
                .build();
    }

    private Cliente toDomain(ClienteEntity entity) {
        return Cliente.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .genero(entity.getGenero())
                .edad(entity.getEdad())
                .identificacion(entity.getIdentificacion())
                .direccion(entity.getDireccion())
                .telefono(entity.getTelefono())
                .clienteId(entity.getClienteId())
                .contrasena(entity.getContrasena())
                .estado(entity.getEstado())
                .build();
    }
}