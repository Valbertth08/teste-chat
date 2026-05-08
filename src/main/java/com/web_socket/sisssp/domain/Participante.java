package com.web_socket.sisssp.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_participantes")
public class Participante {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversa_id")
    private Conversa conversa;
    @Column(name = "usuario_externo_id", nullable = false)
    private String usuarioExternoId;
    private String nome;
    @Column(name = "data_entrada", nullable = false)
    private LocalDateTime dataEntrada;

    public Participante() {
    }

    public Participante(Conversa conversa, String usuarioExternoId, LocalDateTime dataEntrada) {;
        this.conversa = conversa;
        this.usuarioExternoId = usuarioExternoId;
        this.dataEntrada = dataEntrada;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Conversa getConversa() {
        return conversa;
    }

    public void setConversa(Conversa conversa) {
        this.conversa = conversa;
    }

    public String getUsuarioExternoId() {
        return usuarioExternoId;
    }

    public void setUsuarioExternoId(String usuarioExternoId) {
        this.usuarioExternoId = usuarioExternoId;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
