package com.api.sender.apisender.controller;

import com.api.sender.apisender.data.DataPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

// SenderController.java
@RestController
@RequestMapping("/api")
public class SenderController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/send")
    public ResponseEntity<String> sendData(@RequestBody DataPayload payload) {
        // Endpoint para enviar dados para a outra API
        String receiverUrl = "http://localhost:8083/api/receiver";
        String receiverUrlRemote = "http://192.168.0.101:8080/api/receiver";

        // Utilizando uma instância HttpHeaders para definir o tipo de conteúdo da requisição
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Utilizando uma instância HttpEntity para combinar o corpo da requisição e os cabeçalhos
        HttpEntity<DataPayload> request = new HttpEntity<>(payload, headers);

        // Enviando a requisição POST
        ResponseEntity<String> response = restTemplate.postForEntity(receiverUrl, request, String.class);
        return ResponseEntity.ok(response.getBody());
    }
}

