package com.web_socket.sisssp.service;

import com.web_socket.sisssp.domain.Conversa;
import com.web_socket.sisssp.domain.Mensagem;
import com.web_socket.sisssp.domain.Participante;
import com.web_socket.sisssp.dto.MensagemPrivadaEntradaDTO;
import com.web_socket.sisssp.dto.MensagemPrivadaRespostaDTO;
import com.web_socket.sisssp.enums.TipoConversa;
import com.web_socket.sisssp.repository.ConversaRepository;
import com.web_socket.sisssp.repository.MensagemRepository;
import com.web_socket.sisssp.repository.ParticipanteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ChatService {

    @Autowired
    private  ConversaRepository conversaRepository;
    @Autowired
    private  ParticipanteRepository participanteRepository;
    @Autowired
    private  MensagemRepository mensagemRepository;

    @Transactional
    public MensagemPrivadaRespostaDTO salvarMensagemPrivada(MensagemPrivadaEntradaDTO dados, String authenticatedSenderId) {
        Conversa conversa;

        if (dados.conversaId() != null) {
            conversa = conversaRepository.findById(dados.conversaId())
                    .orElseThrow(() -> new RuntimeException("Conversa não encontrada"));
        } else {
            conversa = obterOuCriarConversa(authenticatedSenderId, dados.usuarioDestinatario());
            System.out.println("Conversa obtida/criada com ID: " + conversa.getId());
        }

        Mensagem mensagem = new Mensagem();
        mensagem.setConversa(conversa);
        mensagem.setUsuarioRemetente(authenticatedSenderId);
        mensagem.setMensagem(dados.mensagem());
        mensagem.setDataEnvio(LocalDateTime.now());

        mensagemRepository.save(mensagem);

        return new MensagemPrivadaRespostaDTO(
                mensagem.getId(),
                authenticatedSenderId,
                dados.usuarioDestinatario(),
                mensagem.getMensagem(),
                conversa.getId()
        );
    }
    private synchronized Conversa obterOuCriarConversa(String userA, String userB) {
        return participanteRepository.findExistingConversationId(userA, userB)
                .map(uuid -> conversaRepository.findById(uuid)
                        .orElseThrow(() -> new RuntimeException("Erro de consistência: ID de conversa existe mas registro não encontrado")))
                .orElseGet(() -> criarConversaPrivada(userA, userB));
    }

    private Conversa criarConversaPrivada(String userA, String userB) {
        Conversa conv = new Conversa();
        conv.setTipoConversa(TipoConversa.PRIVADA);
        conv.setDataConversa(LocalDateTime.now());
        final Conversa savedConv = conversaRepository.saveAndFlush(conv);
        Participante p1 = new Participante(savedConv, userA, LocalDateTime.now());
        Participante p2 = new Participante( savedConv, userB, LocalDateTime.now());
        participanteRepository.saveAllAndFlush(List.of(p1, p2));
        return savedConv;
    }
}