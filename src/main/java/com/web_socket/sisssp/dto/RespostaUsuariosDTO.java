package com.web_socket.sisssp.dto;

public record RespostaUsuariosDTO(
        Long id,
        Boolean ativo,
        String matricula,
        String cpf,
        String unidade,
        String nome,
        String login
) {
}
