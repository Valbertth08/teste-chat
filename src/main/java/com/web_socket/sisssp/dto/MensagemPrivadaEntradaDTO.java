package com.web_socket.sisssp.dto;

import java.util.UUID;

public record MensagemPrivadaEntradaDTO(
        String usuarioRemetente,
        String nomeUsuarioRemetente,
        String usuarioDestinatario,
        String mensagem,
        UUID conversaId,
        String nomeUsuarioDestinatario
) {
}
