package com.web_socket.sisssp.controller.rest;

import com.web_socket.sisssp.dto.RespostaUsuariosDTO;
import com.web_socket.sisssp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<RespostaUsuariosDTO>> buscarUsuariosPorFiltro(@RequestParam String filtro) {
        return usuarioService.buscarUsuariosPorFiltro(filtro);

    }
}
