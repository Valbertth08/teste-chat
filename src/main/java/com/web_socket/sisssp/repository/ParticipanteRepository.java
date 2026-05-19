package com.web_socket.sisssp.repository;

import com.web_socket.sisssp.domain.Participante;
import com.web_socket.sisssp.projection.ConversaUsuarioProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    Optional<UUID> encontrarIdDeConversaExistente(String userA, String userB);


    @Query(value = """
        SELECT 
            c.id AS conversa_id,
            c.data_conversa,
            c.tipo_conversa,
            p2.nome AS nome_destinatario
        FROM public.tb_conversas c
        JOIN public.tb_participantes p1 ON c.id = p1.conversa_id
        JOIN public.tb_participantes p2 ON c.id = p2.conversa_id
        WHERE p1.usuario_externo_id = :usuarioId
          AND p2.usuario_externo_id <> :usuarioId
        ORDER BY c.data_conversa DESC
        """, nativeQuery = true)
    Page<ConversaUsuarioProjection> buscarConversasPorUsuario(@Param("usuarioId") String usuarioId, Pageable pageable);

}
