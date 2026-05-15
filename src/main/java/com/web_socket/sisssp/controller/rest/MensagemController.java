package com.web_socket.sisssp.controller.rest;

import com.web_socket.sisssp.anotations.UsuarioLogado;
import com.web_socket.sisssp.projection.ConversaUsuarioProjection;
import com.web_socket.sisssp.projection.MensagemHistoricoProjection;
import com.web_socket.sisssp.service.MensagemService;
import com.web_socket.sisssp.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {

    @Autowired
    private MensagemService mensagemService;

    @GetMapping("/conversas/{conversaId}")
    public ResponseEntity<Page<MensagemHistoricoProjection>> listarMensagensDoUsuario(@PathVariable  UUID conversaId, @UsuarioLogado String sub, Pageable pageable){
        return ResponseEntity.ok(mensagemService.listarHistoricoMensagens(conversaId,sub,pageable));
    }

}
