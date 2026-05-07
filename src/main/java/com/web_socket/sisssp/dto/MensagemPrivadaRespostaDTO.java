package com.web_socket.sisssp.dto;

import java.util.UUID;

public record MensagemPrivadaRespostaDTO(
        UUID id,
        String usuarioRemetente,
        String usuarioDestinatario,
        String mensagem,
        UUID conversaId
) {
}
