package com.web_socket.sisssp.service;

import com.web_socket.sisssp.projection.MensagemHistoricoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface MensagemService {
    Page<MensagemHistoricoProjection> listarHistoricoMensagens(UUID conversaId, String idUsuario, Pageable pageable);

     void marcaMensagensComoLida(UUID conversaId, String idUsuario);
}
