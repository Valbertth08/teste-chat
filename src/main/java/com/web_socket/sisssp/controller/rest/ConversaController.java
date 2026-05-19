package com.web_socket.sisssp.controller.rest;

import com.web_socket.sisssp.anotations.UsuarioLogado;
import com.web_socket.sisssp.dto.RespostaUsuarioLogadoDTO;
import com.web_socket.sisssp.projection.MensagemHistoricoProjection;
import com.web_socket.sisssp.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/conversa")
public class ConversaController {

    @Autowired
    private MensagemService mensagemService;

    @GetMapping("/{conversaId}")
    public ResponseEntity<Page<MensagemHistoricoProjection>> listarMensagensPorConversaEUsuario(@PathVariable  UUID conversaId, @UsuarioLogado RespostaUsuarioLogadoDTO dados, Pageable pageable){
        return ResponseEntity.ok(mensagemService.listarHistoricoMensagens(conversaId,dados.sub(),pageable));
    }
    @GetMapping("/mensagens/nao-lidas")
    public ResponseEntity<Map<UUID, Long>> contarConversasComMensagensNaoLidasPorUsuario(@UsuarioLogado RespostaUsuarioLogadoDTO dados) {
        Map<UUID, Long> conversas = mensagemService.listarConversasComMensagensNaoLidas(dados.sub());
        return ResponseEntity.ok(conversas);
    }
    @PutMapping("/{conversaId}/mensagens-lidas")
    public void marcarMensagensComoLida(@PathVariable UUID conversaId, @UsuarioLogado RespostaUsuarioLogadoDTO dados) {
        mensagemService.marcaMensagensComoLida(conversaId, dados.sub());
    }

}
