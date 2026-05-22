package com.jayasree.rag_app.service;


import com.jayasree.rag_app.dto.EmbeddingRequest;
import com.jayasree.rag_app.dto.EmbeddingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor

public class EmbeddingService {

    private final RestTemplate restTemplate;

    public String generateEmbedding(String text) {

        String url = "http://localhost:5000/embed";

        EmbeddingRequest request =
                new EmbeddingRequest(text);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EmbeddingRequest> entity =
                new HttpEntity<>(request, headers);

        ResponseEntity<EmbeddingResponse> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        entity,
                        EmbeddingResponse.class
                );

        return response.getBody()
                .getEmbedding()
                .toString();
    }
}
