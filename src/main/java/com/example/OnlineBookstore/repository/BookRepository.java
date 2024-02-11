package com.example.OnlineBookstore.repository;

import com.example.OnlineBookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String name);

    List<Book> findByCategoryIgnoreCase(String category);

    long countByQuantity(int quantity);
}
