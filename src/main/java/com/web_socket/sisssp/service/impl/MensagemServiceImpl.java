package com.web_socket.sisssp.service.impl;

import com.web_socket.sisssp.domain.Mensagem;
import com.web_socket.sisssp.enums.StatusMensagem;
import com.web_socket.sisssp.projection.ConversaUsuarioProjection;
import com.web_socket.sisssp.projection.MensagemHistoricoProjection;
import com.web_socket.sisssp.repository.MensagemRepository;
import com.web_socket.sisssp.service.MensagemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MensagemServiceImpl implements MensagemService {
    @Autowired
    private MensagemRepository mensagemRepository;

    @Override
    public Page<MensagemHistoricoProjection> listarHistoricoMensagens(UUID conversaId, String idUsuario, Pageable pageable) {
        return mensagemRepository.buscarHistoricoDeMensagens(conversaId,idUsuario, pageable);
    }

    @Transactional
    @Override
    public void marcaMensagensComoLida(UUID conversaId, String idUsuario) {
        List<Mensagem> mensagensNaoLidas = mensagemRepository
                .encontrarMensagensNaoLidasPorConversa(conversaId, idUsuario);
        if (mensagensNaoLidas.isEmpty()) return;
        mensagensNaoLidas.forEach(mensagem -> mensagem.setStatus(StatusMensagem.LIDA));
        mensagemRepository.saveAll(mensagensNaoLidas);
    }

    @Override
    public Map<UUID, Long> listarConversasComMensagensNaoLidas(String usuarioId) {
        List<Object[]> resultado = mensagemRepository.listarConversasComMensagensNaoLidas(usuarioId);
        return resultado.stream()
                .collect(Collectors.toMap(
                        row -> UUID.fromString(row[0].toString()),
                        row -> ((Number) row[1]).longValue()
                ));
    }


}
