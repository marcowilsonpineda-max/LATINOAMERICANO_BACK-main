package com.banco.latinoamericano.modcuentas.config;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;  //
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Marco
 * @date 25/5/2026
 */
@Configuration
public class RabbitMQConfig {
    @Bean
    public JacksonJsonMessageConverter converter() {  // ✅ cambio por compabilidad marco pineda
        return new JacksonJsonMessageConverter();     // ✅
    }
}

