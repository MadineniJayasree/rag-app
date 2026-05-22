package com.jayasree.rag_app.controller;

import com.jayasree.rag_app.dto.AuthRequest;
import com.jayasree.rag_app.service.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {

    private final JwtService jwtService;

    @PostMapping("/login")
    public String login(
            @RequestBody
            AuthRequest request
    ) {

        if (
                request.getUsername()
                        .equals("admin")
                        &&
                        request.getPassword()
                                .equals("password")
        ) {

            return jwtService.generateToken(
                    request.getUsername()
            );
        }

        throw new RuntimeException(
                "Invalid credentials"
        );
    }
}
