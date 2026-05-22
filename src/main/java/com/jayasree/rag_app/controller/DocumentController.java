package com.jayasree.rag_app.controller;

import com.jayasree.rag_app.dto.QueryResponse;
import com.jayasree.rag_app.dto.QuestionRequest;
import com.jayasree.rag_app.entity.DocumentChunk;
import com.jayasree.rag_app.repository.DocumentChunkRepository;
import com.jayasree.rag_app.service.EmbeddingService;
import com.jayasree.rag_app.service.OllamaService;
import com.jayasree.rag_app.service.PdfService;
import com.jayasree.rag_app.service.RagService;
import com.jayasree.rag_app.util.SimilarityUtils;
import com.jayasree.rag_app.util.TextChunker;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(
        name = "Document APIs",
        description = "RAG document operations"
)
@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor

public class DocumentController {

    private final PdfService pdfService;

    private final EmbeddingService embeddingService;

    private final DocumentChunkRepository repository;

    private final OllamaService ollamaService;
    private final RagService ragService;

    //testing
    @GetMapping("/health")
    public String health() {

        return "Application Running";
    }

    //Upload Pdf
    @Operation(
            summary = "Upload PDF document"
    )
    @PostMapping("/upload")
    public String uploadPdf(
            @RequestParam("file")
            MultipartFile file
    ) {

        String text =
                pdfService.extractText(file);

        List<String> chunks =
                TextChunker.chunkText(
                        text,
                        500,
                        100
                );

        System.out.println(
                "TOTAL CHUNKS = "
                        + chunks.size()
        );

        for (String chunk : chunks) {

            try {

                System.out.println(
                        "PROCESSING CHUNK"
                );

                String embedding =
                        embeddingService
                                .generateEmbedding(chunk);

                System.out.println(
                        "EMBEDDING GENERATED"
                );

                DocumentChunk documentChunk =
                        DocumentChunk.builder()
                                .fileName(
                                        file.getOriginalFilename()
                                )
                                .chunkText(chunk)
                                .embedding(embedding)
                                .build();

                repository.save(documentChunk);

                System.out.println(
                        "SAVED TO DB"
                );

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        return "Document stored successfully";
    }

    //Rag api
    @Operation(summary = "Ask questions from uploaded documents")
    @PostMapping("/query")
    public QueryResponse queryDocument(
            @RequestBody
            QuestionRequest request
    ) {

        return ragService.askQuestion(
                request.getQuestion()
        );
    }
}