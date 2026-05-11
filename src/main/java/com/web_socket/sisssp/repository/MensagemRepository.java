package com.web_socket.sisssp.repository;

import com.web_socket.sisssp.domain.Mensagem;
import com.web_socket.sisssp.projection.MensagemHistoricoProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import java.util.UUID;

public interface MensagemRepository extends JpaRepository<Mensagem, UUID> {

    @Query(value = """
    SELECT m.id AS id,
           m.mensagem AS mensagem,
           m.data_envio AS dataEnvio,
           m.usuario_remetende_id AS remetenteId,
           CASE WHEN m.usuario_remetende_id = :idUsuarioLogado THEN true ELSE false END AS ehRemetente
    FROM tb_mensagens m
    WHERE m.conversation_id = :conversaId
    ORDER BY m.data_envio DESC
    """,
            countQuery = "SELECT count(*) FROM tb_mensagens WHERE conversation_id = :conversaId",
            nativeQuery = true)
    Page<MensagemHistoricoProjection> buscarHistorico(
            @Param("conversaId") UUID conversaId,
            @Param("idUsuarioLogado") String idUsuarioLogado,
            Pageable pageable
    );
}
