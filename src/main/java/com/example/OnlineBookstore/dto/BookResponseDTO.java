package com.example.OnlineBookstore.dto;

import lombok.Builder;

@Builder
public record BookResponseDTO(
        long id,
        String name,
        String author,
        double price,
        String category,
        String description,
        int quantity
) {
}
