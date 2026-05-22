package com.jayasree.rag_app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "document_chunks")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DocumentChunk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Column(columnDefinition = "TEXT")
    private String chunkText;

    @Column(columnDefinition = "vector(384)")
    private String embedding;

//    @Column(columnDefinition = "TEXT")
//    private String embedding;
}


