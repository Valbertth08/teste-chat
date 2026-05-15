package com.web_socket.sisssp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Autowired
    private RabbitAdmin rabbitAdmin;

    public void criarFilaUsuario(String usuarioId,RabbitAdmin rabbitAdmin) {
        Queue fila = QueueBuilder
                .nonDurable("user." + usuarioId)
                .autoDelete()
                .build();
        rabbitAdmin.declareQueue(fila);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
}
