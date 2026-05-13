package com.web_socket.sisssp.service;

import com.web_socket.sisssp.clients.UsuarioFeignClient;
import com.web_socket.sisssp.dto.RespostaUsuariosDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private  final UsuarioFeignClient usuarioFeignClient;

    public UsuarioService(UsuarioFeignClient usuarioFeignClient) {
        this.usuarioFeignClient = usuarioFeignClient;

    }
    public ResponseEntity<List<RespostaUsuariosDTO>> buscarUsuariosPorFiltro(String filtro) {
        return usuarioFeignClient.buscarUsuario(filtro);
    }


}
