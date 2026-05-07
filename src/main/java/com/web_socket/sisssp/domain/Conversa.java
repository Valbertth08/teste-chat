package com.web_socket.sisssp.domain;

import com.web_socket.sisssp.enums.TipoConversa;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_conversas")
public class Conversa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TipoConversa tipoConversa;

    @OneToMany(mappedBy = "conversa", cascade = CascadeType.ALL)
    private List<Participante> participantes;

    private LocalDateTime dataConversa;


    public Conversa() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TipoConversa getTipoConversa() {
        return tipoConversa;
    }

    public void setTipoConversa(TipoConversa tipoConversa) {
        this.tipoConversa = tipoConversa;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    public LocalDateTime getDataConversa() {
        return dataConversa;
    }

    public void setDataConversa(LocalDateTime dataConversa) {
        this.dataConversa = dataConversa;
    }
}
