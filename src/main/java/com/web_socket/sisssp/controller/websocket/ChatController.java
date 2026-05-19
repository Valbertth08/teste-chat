package com.web_socket.sisssp.controller.websocket;


import com.web_socket.sisssp.anotations.UsuarioLogado;
import com.web_socket.sisssp.anotations.UsuarioLogadoResolver;
import com.web_socket.sisssp.dto.MensagemPrivadaEntradaDTO;
import com.web_socket.sisssp.dto.MensagemPrivadaRespostaDTO;
import com.web_socket.sisssp.dto.RespostaUsuarioLogadoDTO;
import com.web_socket.sisssp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    public ChatController(SimpMessagingTemplate messagingTemplate, ChatService chatService) {
        this.messagingTemplate = messagingTemplate;
        this.chatService = chatService;
    }

    @MessageMapping("/enviar-mensagem-privada.send")
    public void enviarMensagemPrivada(@Payload MensagemPrivadaEntradaDTO dados) {
        MensagemPrivadaRespostaDTO respostaDTO = chatService
                .salvarMensagemPrivada(dados.usuarioRemetente(), dados.nomeUsuarioRemetente(), dados.usuarioDestinatario(), dados.nomeUsuarioDestinatario(), dados.conversaId(), dados.mensagem());
        messagingTemplate.convertAndSend(
                "/queue/user." + dados.usuarioDestinatario(),
                respostaDTO
        );
    }
}
