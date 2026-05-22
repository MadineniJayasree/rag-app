package com.jayasree.rag_app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AuthRequest {
    @NotBlank
    private String username;

    private String password;
}
