package com.example.OnlineBookstore.service;

import com.example.OnlineBookstore.dto.BookRequestDTO;
import com.example.OnlineBookstore.dto.BookResponseDTO;

public interface AdminService {
    BookResponseDTO addNewBook(BookRequestDTO bookRequestDTO);

    BookResponseDTO updateBookDetails(BookRequestDTO bookRequestDTO, long id);

    long getStockLevel();

    String checkAvailability(String name);
}
