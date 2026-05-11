package com.web_socket.sisssp.projection;

import java.time.LocalDateTime;
import java.util.UUID;

public interface MensagemHistoricoProjection {
    UUID getId();
    String getMensagem();
    LocalDateTime getDataEnvio();
    String getRemetenteId();
    Boolean getEhRemetente();
}
