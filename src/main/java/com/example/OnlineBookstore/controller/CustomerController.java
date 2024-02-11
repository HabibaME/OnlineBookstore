package com.example.OnlineBookstore.controller;
import com.example.OnlineBookstore.service.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class CustomerController {

    private final CustomerServiceImpl service;

    @GetMapping("/books/{category}")
    public ResponseEntity<?> getBooksByCategory(@PathVariable String category) {
        var response = service.getBooksByCategory(category);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/bookDetails/{name}")
    public ResponseEntity<?> getBookDetailsById(@PathVariable String name) {
        var response = service.getBookDetailsByName(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userName}/{bookName}/{borrowingDate}/{returnDate}")
    public ResponseEntity<?> borrowBook(@PathVariable String userName, @PathVariable String bookName, @PathVariable String borrowingDate, @PathVariable String returnDate) {
        var response = service.borrowBook(userName, bookName, borrowingDate, returnDate);
        return ResponseEntity.ok(response);
    }
}
