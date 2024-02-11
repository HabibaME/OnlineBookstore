package com.example.OnlineBookstore.service;


import com.example.OnlineBookstore.dto.BookResponseDTO;

import java.util.List;

public interface CustomerService {
    List<BookResponseDTO> getBooksByCategory(String category);

    BookResponseDTO getBookDetailsByName(String name);

    String borrowBook(String userName, String bookName, String borrowingDate, String returnDate);
}
