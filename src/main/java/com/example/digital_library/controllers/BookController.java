package com.example.digital_library.controllers;

import com.example.digital_library.dtos.CreateBookDto;
import com.example.digital_library.dtos.ImportBookDto;
import com.example.digital_library.dtos.ReturnBookDto;
import com.example.digital_library.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/livros")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    @Transactional
    public ResponseEntity<ReturnBookDto> createBook(@RequestBody @Valid CreateBookDto createBookDto, UriComponentsBuilder componentsBuilder){
        ReturnBookDto bookReturn = bookService.createBook(createBookDto);
        URI location = componentsBuilder.path("/{id}")
                .buildAndExpand(bookReturn.id())
                .toUri();
        return ResponseEntity.created(location).body(bookReturn);
    }

    @PostMapping("/importar")
    @Transactional
    public ResponseEntity<ReturnBookDto> importBook(@RequestBody ImportBookDto importBookDto) throws IOException {
        ReturnBookDto book = bookService.importBook(importBookDto);
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<Page<ReturnBookDto>> getAllBooks(@PageableDefault(sort = {"id"})Pageable pageable){
        Page<ReturnBookDto> booksReturn = bookService.getAllBooks(pageable);
        return ResponseEntity.ok(booksReturn);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnBookDto> getBookById(@PathVariable Long id){
        ReturnBookDto bookReturn = bookService.getBookById(id);
        return ResponseEntity.ok(bookReturn);
    }

    @GetMapping("/search")
    public ResponseEntity<ReturnBookDto> getBookByTitle(@RequestParam String title){
        ReturnBookDto bookReturn = bookService.getBookByTitle(title);
        return ResponseEntity.ok(bookReturn);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ReturnBookDto> updateBook(@RequestBody @Valid CreateBookDto updateBook, @PathVariable Long id){
        ReturnBookDto bookReturn = bookService.updateBook(updateBook, id);
        return ResponseEntity.ok(bookReturn);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }






}
