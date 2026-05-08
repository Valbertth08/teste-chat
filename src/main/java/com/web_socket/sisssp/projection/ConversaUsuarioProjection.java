package com.web_socket.sisssp.projection;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ConversaUsuarioProjection {
    UUID getConversa_id();
    LocalDateTime getData_conversa();
    String getTipo_conversa();
    String getNome_destinatario();
}
