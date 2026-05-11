package com.web_socket.sisssp.clients;
import com.web_socket.sisssp.clients.config.UsuarioClientConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "userService", url = "${users.service.url}", configuration = UsuarioClientConfig.class)
public class UsuarioFeignClient {
}
