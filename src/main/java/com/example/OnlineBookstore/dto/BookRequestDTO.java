package com.example.OnlineBookstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record BookRequestDTO(
        @NotBlank(message = "The name of the book is required")
        String name,
        @NotBlank(message = "The name of the author is required")
        String author,
        @NotEmpty
        double price,
        @NotBlank(message = "category is required")
        String category,
        @NotBlank(message = "The description is required")
        String description

) {
}
