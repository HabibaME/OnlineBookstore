package com.example.OnlineBookstore.service.impl;
import com.example.OnlineBookstore.dto.BookResponseDTO;
import com.example.OnlineBookstore.entity.Book;
import com.example.OnlineBookstore.entity.Booking;
import com.example.OnlineBookstore.entity.User;
import com.example.OnlineBookstore.error.ApiRequestException;
import com.example.OnlineBookstore.error.RecordNotFoundException;
import com.example.OnlineBookstore.mapper.Mapper;
import com.example.OnlineBookstore.repository.BookRepository;
import com.example.OnlineBookstore.repository.BookingRepository;
import com.example.OnlineBookstore.repository.UserRepository;
import com.example.OnlineBookstore.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final BookRepository repository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final Mapper mapper;
    private final AdminServiceImpl adminService;

    @Override
    public List<BookResponseDTO> getBooksByCategory(String category) {
        List<BookResponseDTO> books = repository.findByCategoryIgnoreCase(category).stream()
                .map(book -> mapper.convertBookModelToBookResponseDTO(book))
                .collect(Collectors.toList());
        if (books.isEmpty()) {
            throw new ApiRequestException("No book match this category" + category);
        }
        return books;
    }

    @Override
    public BookResponseDTO getBookDetailsByName(String name) {
        Book book = repository.findByName(name);
        if (book != null) {
            return mapper.convertBookModelToBookResponseDTO(book);
        } else {
            throw new RecordNotFoundException("there is no book match this = " + name);
        }
    }
    @Override
    public String borrowBook(String userName, String bookName, String borrowingDate, String returnDate) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new RecordNotFoundException("there is no user with this name ");
        }
        Booking booking = new Booking();
        Book book = repository.findByName(bookName);
        if (book != null) {
            if (book.getQuantity() != 0) {
                booking.setUser(user);
                booking.setBook(book);
                booking.setReturnDate(returnDate);
                booking.setBorrowingDate(borrowingDate);
                bookingRepository.save(booking);
                book.setQuantity(0);
                adminService.updateBookDetails(mapper.convertBookModelToBookRequestDTO(book), book.getId());
                return "The book is borrowed to you , please return it before " + returnDate;
            } else {
                return "This book is not available now , please try again later";
            }
        } else {
            throw new RecordNotFoundException("there is no book match this book name");
        }
    }
}
