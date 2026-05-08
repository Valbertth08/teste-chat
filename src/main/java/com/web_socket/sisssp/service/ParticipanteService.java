package com.web_socket.sisssp.service;

import com.web_socket.sisssp.projection.ConversaUsuarioProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ParticipanteService {

    Page<ConversaUsuarioProjection> listarConversasDoUsuario(String idUsuario, Pageable pageable);


}
