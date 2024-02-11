package com.example.OnlineBookstore.service;

import com.example.OnlineBookstore.dto.BookRequestDTO;
import com.example.OnlineBookstore.dto.BookResponseDTO;
import com.example.OnlineBookstore.entity.Book;
import com.example.OnlineBookstore.mapper.Mapper;
import com.example.OnlineBookstore.repository.BookRepository;
import com.example.OnlineBookstore.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private Mapper mapper;
    @InjectMocks
    private CustomerServiceImpl customerService;
    Book book;
    BookRequestDTO bookRequestDTO;
    BookResponseDTO bookResponseDTO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        book = Book.builder()
                .id(1)
                .name("Girl With a Pearl Earring")
                .author("Tracy Chevalier")
                .price(70.0)
                .category("ART")
                .description("The book that inspired a play, a film and thousands of mini-breaks to The Hague. Looking at the Vermeer painting of the same name, Chevalier was inspired by the latent intensity of the sitter’s gaze as it meets the viewer/artist.")
                .quantity(1)
                .build();
        bookRequestDTO = BookRequestDTO.builder().name("Girl With a Pearl Earring")
                .author("Tracy Chevalier")
                .price(70.0)
                .category("ART")
                .description("The book that inspired a play, a film and thousands of mini-breaks to The Hague. Looking at the Vermeer painting of the same name, Chevalier was inspired by the latent intensity of the sitter’s gaze as it meets the viewer/artist.")
                .build();
        bookResponseDTO = BookResponseDTO.builder().id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .category(book.getCategory())
                .description(book.getDescription())
                .quantity(book.getQuantity())
                .price(book.getPrice()).build();
    }

    @Test
    public void givenBookCategory_whenGetBooks_thenReturnListOfBookRequestDTO() {
        Book book2 = Book.builder()
                .id(2)
                .name("To the Lighthouse")
                .author("Virginia Woolf")
                .price(60.0)
                .category("ART")
                .description("Woolf’s stream-of-consciousness meditation on life, love and the nature of memory is also a story about the uncertainty and struggle of creativity.")
                .quantity(1)
                .build();
        String category = book.getCategory();
        when(bookRepository.findByCategoryIgnoreCase(category)).thenReturn(List.of(book, book2));
        when(mapper.convertBookModelToBookResponseDTO(book)).thenReturn(bookResponseDTO);
        List<BookResponseDTO> response = customerService.getBooksByCategory(category);
        assertEquals(response.get(0), bookResponseDTO);

    }

    @Test
    public void givenBookName_whenGetBook_thenReturnBookRequestDTO() {
        String bookName = book.getName();
        when(bookRepository.findByName(bookName)).thenReturn(book);
        when(mapper.convertBookModelToBookResponseDTO(book)).thenReturn(bookResponseDTO);
        BookResponseDTO response = customerService.getBookDetailsByName(bookName);
        assertEquals(response, bookResponseDTO);
    }
}
