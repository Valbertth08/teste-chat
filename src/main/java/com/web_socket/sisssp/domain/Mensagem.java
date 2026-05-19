package com.web_socket.sisssp.domain;

import com.web_socket.sisssp.enums.StatusMensagem;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_mensagens")
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    private Conversa conversa;

    @Column(name = "usuario_remetente_id")
    private String usuarioRemetente;
    @Column(name = "mensagem", nullable = false)
    private String mensagem;
    private LocalDateTime dataEnvio;

    @Enumerated(EnumType.STRING)
    private StatusMensagem status;

    public Mensagem() {
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getUsuarioRemetente() {
        return usuarioRemetente;
    }

    public void setUsuarioRemetente(String usuarioRemetente) {
        this.usuarioRemetente = usuarioRemetente;
    }

    public Conversa getConversa() {
        return conversa;
    }

    public void setConversa(Conversa conversa) {
        this.conversa = conversa;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public StatusMensagem getStatus() {
        return status;
    }

    public void setStatus(StatusMensagem status) {
        this.status = status;
    }
}
