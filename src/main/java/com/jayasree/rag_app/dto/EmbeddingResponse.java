package com.jayasree.rag_app.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EmbeddingResponse {

    private List<Double> embedding;
}
