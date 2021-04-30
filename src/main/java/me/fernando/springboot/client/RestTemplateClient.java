package me.fernando.springboot.client;

import me.fernando.springboot.domain.Funcionario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RestTemplateClient {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        ResponseEntity<List<Funcionario>> exchange = new RestTemplate()
                .exchange("http://localhost:8080/funcionario/listAll", HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {});

        logger.info(exchange.getBody());
    }
}
