package com.web_socket.sisssp.service;

import com.web_socket.sisssp.domain.Conversa;
import com.web_socket.sisssp.domain.Mensagem;
import com.web_socket.sisssp.domain.Participante;
import com.web_socket.sisssp.dto.MensagemPrivadaEntradaDTO;
import com.web_socket.sisssp.dto.MensagemPrivadaRespostaDTO;
import com.web_socket.sisssp.enums.StatusMensagem;
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
    public MensagemPrivadaRespostaDTO salvarMensagemPrivada(String usuarioRemetente,String nomeUsuarioRemetente, String usuarioDestinatario, String nomeUsuarioDestinatario, UUID conversaId, String conteudo) {
        Conversa conversa;

        if (conversaId != null) {
            conversa = conversaRepository.findById(conversaId)
                    .orElseThrow(() -> new RuntimeException("Conversa não encontrada"));
        } else {
            conversa = obterOuCriarConversa(usuarioRemetente, nomeUsuarioRemetente, usuarioDestinatario, nomeUsuarioDestinatario);
        }

        Mensagem mensagem = new Mensagem();
        mensagem.setConversa(conversa);
        mensagem.setUsuarioRemetente(usuarioRemetente);
        mensagem.setMensagem(conteudo);
        mensagem.setDataEnvio(LocalDateTime.now());
        mensagem.setStatus(StatusMensagem.NAO_LIDA);

        mensagemRepository.save(mensagem);

        return new MensagemPrivadaRespostaDTO(
                mensagem.getId(),
                usuarioRemetente,
                usuarioDestinatario,
                mensagem.getMensagem(),
                conversa.getId(),
                mensagem.getDataEnvio(),
                nomeUsuarioDestinatario
        );
    }
    private synchronized Conversa obterOuCriarConversa(String usuarioRemetente, String nomeUsuarioRemetente, String usuarioDestinatario, String nomeUsuarioDestinatario) {
        return participanteRepository.encontrarIdDeConversaExistente(usuarioRemetente, usuarioDestinatario)
                .map(uuid -> conversaRepository.findById(uuid)
                        .orElseThrow(() -> new RuntimeException("Erro de consistência: ID de conversa existe mas registro não encontrado")))
                .orElseGet(() -> criarConversaPrivada(usuarioRemetente,nomeUsuarioRemetente, usuarioDestinatario,nomeUsuarioDestinatario));
    }

    private Conversa criarConversaPrivada(String usuarioRemetente,String nomeUsuarioRemetente, String usuarioDestinatario, String nomeUsuarioDestinatario) {
        Conversa conv = new Conversa();
        conv.setTipoConversa(TipoConversa.PRIVADA);
        conv.setDataConversa(LocalDateTime.now());
        final Conversa savedConv = conversaRepository.saveAndFlush(conv);
        Participante p1 = new Participante(savedConv, usuarioRemetente, LocalDateTime.now(),nomeUsuarioRemetente);
        Participante p2 = new Participante( savedConv, usuarioDestinatario, LocalDateTime.now(),nomeUsuarioDestinatario);
        participanteRepository.saveAllAndFlush(List.of(p1, p2));
        return savedConv;
    }
}