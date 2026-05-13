package com.web_socket.sisssp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SissspApplication {

	public static void main(String[] args) {
		SpringApplication.run(SissspApplication.class, args);
	}

}
