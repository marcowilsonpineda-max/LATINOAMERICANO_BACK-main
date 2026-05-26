package com.banco.latinoamericano.modclientes.config;

import org.mockito.Mockito;

import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author Marco
 * @date 25/5/2026
 */

@Configuration
public class RabbitMockConfig
{
    @Bean
    public RabbitTemplate rabbitTemplate() {
        // Retornamos un objeto simulado para que el constructor de ClienteService no falle
        return Mockito.mock(RabbitTemplate.class);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return Mockito.mock(ConnectionFactory.class);
    }
}
