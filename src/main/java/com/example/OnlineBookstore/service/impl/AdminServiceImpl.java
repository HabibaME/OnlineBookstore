package com.example.OnlineBookstore.service.impl;

import com.example.OnlineBookstore.dto.BookRequestDTO;
import com.example.OnlineBookstore.dto.BookResponseDTO;
import com.example.OnlineBookstore.entity.Book;
import com.example.OnlineBookstore.error.DuplicationRecordException;
import com.example.OnlineBookstore.error.RecordNotFoundException;
import com.example.OnlineBookstore.mapper.Mapper;
import com.example.OnlineBookstore.repository.BookRepository;
import com.example.OnlineBookstore.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {
    private final BookRepository repository;
    private final Mapper mapper;

    @Override
    public BookResponseDTO addNewBook(BookRequestDTO bookRequestDTO) {
        Book storedBook = repository.findByName(bookRequestDTO.name());
        if (storedBook != null)
            throw new DuplicationRecordException("Oops! this Question is already in your database");
        Book savedData = mapper.convertBookRequestDTOtoBookModel(bookRequestDTO);
        savedData = repository.save(savedData);
        return mapper.convertBookModelToBookResponseDTO(savedData);
    }

    @Override
    public BookResponseDTO updateBookDetails(BookRequestDTO bookRequestDTO, long id) {
        Book book = repository.findById(id).orElseThrow(
                () -> new RecordNotFoundException("Book not found with id = " + id)
        );
        book.setCategory(bookRequestDTO.category());
        book.setName(bookRequestDTO.name());
        book.setAuthor(bookRequestDTO.author());
        book.setPrice(bookRequestDTO.price());
        book.setDescription(bookRequestDTO.description());
        book = repository.save(book);
        return mapper.convertBookModelToBookResponseDTO(book);
    }

    @Override
    public long getStockLevel() {
        int quantity = 1;
        return repository.countByQuantity(quantity);
    }

    @Override
    public String checkAvailability(String name) {
        Book book = repository.findByName(name);
        if (book != null) {
            if (book.getQuantity() != 0) {
                return "This book is available";
            } else {
                return "this book is not available now";
            }
        } else {
            throw new RecordNotFoundException("Book not found with name = " + name);
        }
    }

}
