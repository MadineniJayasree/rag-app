package com.jayasree.rag_app.repository;


import com.jayasree.rag_app.entity.DocumentChunk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentChunkRepository
        extends JpaRepository<DocumentChunk, Long> {
    @Query(
            value = """
                    SELECT *
                    FROM document_chunks
                    ORDER BY embedding <=> CAST(:embedding AS vector)
                    LIMIT 2
                    """,
            nativeQuery = true
    )

    List<DocumentChunk> findSimilarChunks(
            @Param("embedding")
            String embedding
    );

}
