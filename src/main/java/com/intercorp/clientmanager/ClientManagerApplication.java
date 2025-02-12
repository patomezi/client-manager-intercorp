package com.intercorp.clientmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ClientManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientManagerApplication.class, args);
    }

}
