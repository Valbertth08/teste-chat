package com.web_socket.sisssp.controller.rest;

import com.web_socket.sisssp.anotations.UsuarioLogado;
import com.web_socket.sisssp.dto.RespostaUsuarioLogadoDTO;
import com.web_socket.sisssp.projection.ConversaUsuarioProjection;

import com.web_socket.sisssp.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/participante")
public class ParticipanteController {

    @Autowired
    private ParticipanteService participanteService;

    @GetMapping("/conversas")
    public ResponseEntity<Page<ConversaUsuarioProjection>> listarConversasDoUsuario(@UsuarioLogado RespostaUsuarioLogadoDTO dados, Pageable pageable) {
        System.out.println("ID do usuário logado: " + dados.sub());
        return ResponseEntity.ok(participanteService.listarConversasDoUsuario(dados.sub(), pageable));

    }

}
