package com.example.demo.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskUpdateRequest(
        @NotNull
        Long id,
        @NotNull
        String name,
        @NotNull
        String description,
        @NotNull
        @JsonFormat(pattern = "dd:MM:yyyy")
        LocalDateTime dueDate,
        @NotNull
        Long uid

) {
}
