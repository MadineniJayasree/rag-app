package com.jayasree.rag_app.util;

import java.util.ArrayList;
import java.util.List;

public class SimilarityUtils {

    public static List<Double> parseEmbedding(
            String embedding
    ) {

        embedding = embedding
                .replace("[", "")
                .replace("]", "");

        String[] values =
                embedding.split(",");

        List<Double> vector =
                new ArrayList<>();

        for (String value : values) {

            vector.add(
                    Double.parseDouble(
                            value.trim()
                    )
            );
        }

        return vector;
    }

    public static double cosineSimilarity(
            List<Double> vec1,
            List<Double> vec2
    ) {

        double dot = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;

        for (int i = 0; i < vec1.size(); i++) {

            dot += vec1.get(i) * vec2.get(i);

            norm1 += Math.pow(vec1.get(i), 2);

            norm2 += Math.pow(vec2.get(i), 2);
        }

        return dot / (
                Math.sqrt(norm1)
                        * Math.sqrt(norm2)
        );
    }
}
