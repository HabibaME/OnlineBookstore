package com.example.OnlineBookstore.dto;

import lombok.Builder;

@Builder
public record BookRequestDTO(String name,
                             String author,
                             double price,
                             String category,
                             String description

) {
}
