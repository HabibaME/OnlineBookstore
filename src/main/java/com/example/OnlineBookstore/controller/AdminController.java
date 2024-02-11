package com.example.OnlineBookstore.controller;
import com.example.OnlineBookstore.dto.BookRequestDTO;
import com.example.OnlineBookstore.service.impl.AdminServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AdminController {

    private final AdminServiceImpl service;

    @PostMapping("/newBook")
    public ResponseEntity<?> addBook(@RequestBody BookRequestDTO bookRequestDTO) {
        var response = service.addNewBook(bookRequestDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBookDetails(@RequestBody BookRequestDTO bookRequestDTO, @PathVariable long id) {
        var response = service.updateBookDetails(bookRequestDTO, id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stockLevel")
    public long getStockLevel() {
        return this.service.getStockLevel();
    }

    @GetMapping("/availability/{name}")
    public String checkAvailability(@PathVariable String name) {
        return this.service.checkAvailability(name);
    }


}
