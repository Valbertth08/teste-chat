package com.web_socket.sisssp.controller.websocket;

import com.web_socket.sisssp.config.RabbitMqConfig;
import com.web_socket.sisssp.dto.MensagemPrivadaEntradaDTO;
import com.web_socket.sisssp.dto.MensagemPrivadaRespostaDTO;
import com.web_socket.sisssp.service.ChatService;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;
    private final RabbitAdmin rabbitAdmin;
    private final RabbitMqConfig rabbitMqConfig;

    public ChatController(SimpMessagingTemplate messagingTemplate, ChatService chatService, RabbitAdmin rabbitAdmin, RabbitMqConfig rabbitMqConfig) {
        this.messagingTemplate = messagingTemplate;
        this.chatService = chatService;
        this.rabbitAdmin = rabbitAdmin;
        this.rabbitMqConfig = rabbitMqConfig;
    }

    @MessageMapping("/enviar-mensagem-privada.send")
    public void enviarMensagemPrivada(@Payload MensagemPrivadaEntradaDTO dados) {
        rabbitMqConfig.criarFilaUsuario(dados.usuarioDestinatario(), rabbitAdmin);
        MensagemPrivadaRespostaDTO respostaDTO = chatService
                .salvarMensagemPrivada(dados, dados.usuarioRemetente());
        messagingTemplate.convertAndSend(
                "/queue/user." + dados.usuarioDestinatario(),
                respostaDTO
        );
    }
}
