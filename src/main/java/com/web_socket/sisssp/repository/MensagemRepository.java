package com.web_socket.sisssp.repository;

import com.web_socket.sisssp.domain.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MensagemRepository extends JpaRepository<Mensagem, UUID> {
}
