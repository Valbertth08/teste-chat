package com.web_socket.sisssp.service.impl;

import com.web_socket.sisssp.projection.ConversaUsuarioProjection;
import com.web_socket.sisssp.repository.ParticipanteRepository;
import com.web_socket.sisssp.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ParticipanteServiceImpl implements ParticipanteService {
    @Autowired
    private ParticipanteRepository participanteRepository;
    @Override
    public Page<ConversaUsuarioProjection> listarConversasDoUsuario(String idUsuario, Pageable pageable) {
        return participanteRepository.buscarConversasPorUsuario(idUsuario, pageable);
    }
}
