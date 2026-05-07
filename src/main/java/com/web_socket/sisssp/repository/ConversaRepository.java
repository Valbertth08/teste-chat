package com.web_socket.sisssp.repository;

import com.web_socket.sisssp.domain.Conversa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConversaRepository extends JpaRepository<Conversa, UUID> {
}
