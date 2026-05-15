package com.web_socket.sisssp.service.impl;

import com.web_socket.sisssp.projection.ConversaUsuarioProjection;
import com.web_socket.sisssp.projection.MensagemHistoricoProjection;
import com.web_socket.sisssp.repository.MensagemRepository;
import com.web_socket.sisssp.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MensagemServiceImpl implements MensagemService {
    @Autowired
    private MensagemRepository mensagemRepository;

    @Override
    public Page<MensagemHistoricoProjection> listarHistoricoMensagens(UUID conversaId, String idUsuario, Pageable pageable) {
        return mensagemRepository.buscarHistorico(conversaId,idUsuario, pageable);
    }


}
