package com.web_socket.sisssp.repository;

import com.web_socket.sisssp.domain.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ParticipanteRepository extends JpaRepository<Participante, UUID> {

    @Query(value = """
       SELECT p1.conversa_id
       FROM public.tb_participantes p1
       JOIN public.tb_participantes p2 ON p1.conversa_id = p2.conversa_id
       JOIN public.tb_conversas c ON p1.conversa_id = c.id
       WHERE p1.usuario_externo_id = :userA
         AND p2.usuario_externo_id = :userB
         AND c.tipo_conversa = 'PRIVADA'
       LIMIT 1
    """, nativeQuery = true)
    Optional<UUID> findExistingConversationId(String userA, String userB);
}
