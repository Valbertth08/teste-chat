package com.web_socket.sisssp.dto;

import java.util.UUID;

public record MensagemPrivadaEntradaDTO(
        String usuarioRemetente,
        String usuarioDestinatario,
        String mensagem,
        UUID conversaId
) {
}
