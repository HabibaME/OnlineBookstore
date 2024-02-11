package com.example.OnlineBookstore.mapper;
import com.example.OnlineBookstore.dto.BookRequestDTO;
import com.example.OnlineBookstore.dto.BookResponseDTO;
import com.example.OnlineBookstore.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public BookResponseDTO convertBookModelToBookResponseDTO(Book book) {
        return BookResponseDTO.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .category(book.getCategory())
                .description(book.getDescription())
                .quantity(book.getQuantity())
                .price(book.getPrice()).build();
    }

    public BookRequestDTO convertBookModelToBookRequestDTO(Book book) {
        return BookRequestDTO.builder()
                .name(book.getName())
                .author(book.getAuthor())
                .category(book.getCategory())
                .description(book.getDescription())
                .price(book.getPrice()).build();
    }

    public Book convertBookRequestDTOtoBookModel(BookRequestDTO bookRequestDTO) {
        return Book.builder()
                .name(bookRequestDTO.name())
                .author(bookRequestDTO.author())
                .category(bookRequestDTO.category())
                .description(bookRequestDTO.description())
                .quantity(1)
                .price(bookRequestDTO.price()).build();
    }


}
