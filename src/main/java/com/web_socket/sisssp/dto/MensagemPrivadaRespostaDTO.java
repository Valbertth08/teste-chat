package com.web_socket.sisssp.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record MensagemPrivadaRespostaDTO(
        UUID id,
        String usuarioRemetente,
        String usuarioDestinatario,
        String mensagem,
        UUID conversaId,
        LocalDateTime dataEnvio
) {
}
