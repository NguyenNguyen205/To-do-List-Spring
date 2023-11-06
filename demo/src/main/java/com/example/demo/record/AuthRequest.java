package com.example.demo.record;

import jakarta.validation.constraints.NotNull;

public record AuthRequest(
        @NotNull
        String username,
        @NotNull
        String password
) {
}
