package com.example.demo.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskCreateRequest(
        @NotNull
        String name,
        @NotNull
        String detail,
        @NotNull
        @JsonFormat(pattern = "dd:MM:yyyy")
        LocalDateTime dueDate,
        @NotNull
        Long uid
) {

}
