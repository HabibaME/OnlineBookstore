package com.example.OnlineBookstore.service;

import com.example.OnlineBookstore.dto.BookRequestDTO;
import com.example.OnlineBookstore.dto.BookResponseDTO;
import com.example.OnlineBookstore.entity.Book;
import com.example.OnlineBookstore.mapper.Mapper;
import com.example.OnlineBookstore.repository.BookRepository;
import com.example.OnlineBookstore.service.impl.AdminServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AdminServiceTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private Mapper mapper;
    @InjectMocks
    private AdminServiceImpl adminService;
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
    public void givenBookRequestDToO_whenAddBook_thenReturnBookResponseDTO() {
        String name = bookRequestDTO.name();
        when(mapper.convertBookRequestDTOtoBookModel(bookRequestDTO)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(mapper.convertBookModelToBookResponseDTO(book)).thenReturn(bookResponseDTO);
        BookResponseDTO response = adminService.addNewBook(bookRequestDTO);
        assertEquals(response, bookResponseDTO);
    }

    @Test
    public void givenBookRequestDTOAndBookId_whenUpdateBook_thenReturnUpdatedBookResponseDTO() {
        BookRequestDTO updatedBookRequestDTO = BookRequestDTO.builder().name("Girl With a Pearl Earring")
                .author("Tracy Chevalier")
                .price(400.0)
                .category("ART")
                .description("The book that inspired a play, a film and thousands of mini-breaks to The Hague. Looking at the Vermeer painting of the same name, Chevalier was inspired by the latent intensity of the sitter’s gaze as it meets the viewer/artist.")
                .build();
        Book updatedBookSaved = Book.builder().id(1).name(updatedBookRequestDTO.name())
                .author(updatedBookRequestDTO.author())
                .price(updatedBookRequestDTO.price())
                .category(updatedBookRequestDTO.category())
                .quantity(1)
                .description(updatedBookRequestDTO.description())
                .build();
        BookResponseDTO updatedBookResponseDTO = BookResponseDTO.builder().id(1).name(updatedBookRequestDTO.name())
                .author(updatedBookRequestDTO.author())
                .price(updatedBookRequestDTO.price())
                .category(updatedBookRequestDTO.category())
                .quantity(1)
                .description(updatedBookRequestDTO.description())
                .build();
        long id = book.getId();
        when(bookRepository.findById(id)).thenReturn(Optional.ofNullable(book));
        when(bookRepository.save(updatedBookSaved)).thenReturn(updatedBookSaved);
        when(mapper.convertBookModelToBookResponseDTO(updatedBookSaved)).thenReturn(updatedBookResponseDTO);
        BookResponseDTO response = adminService.updateBookDetails(updatedBookRequestDTO, id);
        assertEquals(response, updatedBookResponseDTO);
    }

    @Test
    public void givenBookQuantity_whenCountBooks_thenReturnNumberOfBooks() {
        int quantity = 1;
        long count = 3;
        when(bookRepository.countByQuantity(1)).thenReturn(count);
        long response = adminService.getStockLevel();
        assertEquals(response, count);
    }

    @Test
    public void givenBookName_whenCheckAvailability_thenReturnMessage() {
        String name = "Girl With a Pearl Earring";
        when(bookRepository.findByName(name)).thenReturn(book);
        int quantity = 1;
        String response = adminService.checkAvailability(name);
        assertEquals(response, "This book is available");

    }


}

