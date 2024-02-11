package com.example.OnlineBookstore.repository;

import com.example.OnlineBookstore.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void bookRepository_findByName_returnBook() {
        Book book = bookRepository.findByName("Girl With a Pearl Earring");
        Book book1 = Book.builder()
                .id(1)
                .name("Girl With a Pearl Earring")
                .author("Tracy Chevalier")
                .price(70.0)
                .category("ART")
                .description("The book that inspired a play, a film and thousands of mini-breaks to The Hague. Looking at the Vermeer painting of the same name, Chevalier was inspired by the latent intensity of the sitter’s gaze as it meets the viewer/artist.")
                .quantity(1)
                .build();
        assertEquals(book1, book);

    }

    @Test
    public void bookRepository_findByCategoryIgnoreCase_returnBooks() {
        List<Book> books = bookRepository.findByCategoryIgnoreCase("ART");
        Book book = Book.builder()
                .id(1)
                .name("Girl With a Pearl Earring")
                .author("Tracy Chevalier")
                .price(70.0)
                .category("ART")
                .description("The book that inspired a play, a film and thousands of mini-breaks to The Hague. Looking at the Vermeer painting of the same name, Chevalier was inspired by the latent intensity of the sitter’s gaze as it meets the viewer/artist.")
                .quantity(1)
                .build();
        assertEquals(book, books.get(0));

    }

    @Test
    public void bookRepository_countByQuantity_returnCount() {
        long count = bookRepository.countByQuantity(1);
        long expectedCount = 3;
        assertEquals(3, count);
    }
}
