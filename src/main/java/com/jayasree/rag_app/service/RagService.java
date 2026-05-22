package com.jayasree.rag_app.service;

import com.jayasree.rag_app.dto.QueryResponse;
import com.jayasree.rag_app.entity.DocumentChunk;
import com.jayasree.rag_app.repository.DocumentChunkRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class RagService {

    private final EmbeddingService embeddingService;

    private final DocumentChunkRepository repository;

    private final OllamaService ollamaService;

    public QueryResponse askQuestion(
            String question
    ) {

        // Generate embedding for user question
        String queryEmbedding =
                embeddingService.generateEmbedding(
                        question
                );

        // Find similar chunks using pgvector
        List<DocumentChunk> similarChunks =
                repository.findSimilarChunks(
                        queryEmbedding
                );

        // Extract chunk text
        List<String> topChunks =
                similarChunks.stream()

                        .map(
                                DocumentChunk::getChunkText
                        )

                        .toList();

        // Build context
        String context =
                String.join(
                        "\n",
                        topChunks
                );

        // Build prompt
        String prompt = """
                Answer briefly using context.

                Context:
                %s

                Question:
                %s
                """.formatted(
                context,
                question
        );

        // Generate AI response
        String answer =
                ollamaService.generateAnswer(
                        prompt
                );

        // Return response DTO
        return new QueryResponse(
                answer
        );
    }
}