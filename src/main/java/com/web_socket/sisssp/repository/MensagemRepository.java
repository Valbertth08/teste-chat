package com.web_socket.sisssp.repository;

import com.web_socket.sisssp.domain.Mensagem;
import com.web_socket.sisssp.projection.MensagemHistoricoProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface MensagemRepository extends JpaRepository<Mensagem, UUID> {

    @Query(value = """
    SELECT m.id AS id,
           m.mensagem AS mensagem,
           m.data_envio AS dataEnvio,
           m.usuario_remetente_id AS remetenteId,
           CASE WHEN m.usuario_remetente_id = :idUsuarioLogado THEN true ELSE false END AS ehRemetente
    FROM tb_mensagens m
    WHERE m.conversation_id = :conversaId
    ORDER BY m.data_envio DESC
    """,
            countQuery = "SELECT count(*) FROM tb_mensagens WHERE conversation_id = :conversaId",
            nativeQuery = true)
    Page<MensagemHistoricoProjection> buscarHistoricoDeMensagens(
            @Param("conversaId") UUID conversaId,
            @Param("idUsuarioLogado") String idUsuarioLogado,
            Pageable pageable
    );
    @Modifying
    @Transactional
    @Query(value = """
        UPDATE tb_mensagens m
        SET status = 'LIDA'
        FROM tb_participantes p
        WHERE m.conversation_id = :conversaId
        AND p.conversa_id = :conversaId
        AND p.usuario_externo_id = :usuarioId
        AND m.usuario_remetente_id != :usuarioId
        AND m.lida = false
    """, nativeQuery = true)
    void marcarMensagensComoLida(  @Param("conversaId") UUID conversaId, @Param("usuarioId") String idUsuario);


    @Query(value = """
    SELECT 
        m.conversation_id as conversaId,
        COUNT(m.id) as totalNaoLidas
    FROM tb_mensagens m
    INNER JOIN tb_participantes p 
        ON p.conversa_id = m.conversation_id
    WHERE p.usuario_externo_id = :usuarioId
    AND m.usuario_remetente_id != :usuarioId
    AND m.status= 'NAO_LIDA'
    GROUP BY m.conversation_id
""", nativeQuery = true)
    List<Object[]> listarConversasComMensagensNaoLidas(@Param("usuarioId") String usuarioId);

    @Query(value = """
    SELECT m.* 
    FROM tb_mensagens m
    INNER JOIN tb_participantes p 
        ON p.conversa_id = m.conversation_id
    WHERE m.conversation_id = :conversaId
    AND p.usuario_externo_id = :usuarioId
    AND m.usuario_remetente_id != :usuarioId
    AND m.status != 'LIDA'
""", nativeQuery = true)
    List<Mensagem> encontrarMensagensNaoLidasPorConversa(
            @Param("conversaId") UUID conversaId,
            @Param("usuarioId") String usuarioId
    );

}
