package com.jayasree.rag_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor

public class OllamaService {

    private final RestTemplate restTemplate;

    public String generateAnswer(String prompt) {

        String url =
                "http://localhost:11434/api/generate";

        Map<String, Object> request =
                Map.of(
                        "model", "tinyllama",
                        "prompt", prompt,
                        "stream", false
                );

        HttpHeaders headers =
                new HttpHeaders();

        headers.setContentType(
                MediaType.APPLICATION_JSON
        );

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(request, headers);

        ResponseEntity<Map> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        entity,
                        Map.class
                );

        return response.getBody()
                .get("response")
                .toString();
    }
}
