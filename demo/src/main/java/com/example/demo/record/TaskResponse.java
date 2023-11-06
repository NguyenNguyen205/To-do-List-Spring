package com.example.demo.record;

import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TaskResponse(
        @NotNull
        String name,
        @NotNull
        String detail,
        @NotNull
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDateTime createdDate,
        @JsonFormat(pattern = "dd-MM-YYYY")
        LocalDateTime updateDate,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDateTime dueDate,
        @NotNull
        Long uid,
        @NotNull
        String username
) {


}
