package com.example.digital_library.controllers;


import com.example.digital_library.dtos.CreateAuthorDto;
import com.example.digital_library.dtos.ReturnAuthorDto;
import com.example.digital_library.dtos.ReturnBookDto;
import com.example.digital_library.services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/autores")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    @Transactional
    public ResponseEntity<ReturnAuthorDto> createAuthor(@RequestBody @Valid CreateAuthorDto author, UriComponentsBuilder uriBuilder){
        ReturnAuthorDto authorReturn = authorService.createAuthor(author);
        URI location = uriBuilder
                .path("/{id}")
                .buildAndExpand(authorReturn.id())
                .toUri();
        return ResponseEntity.created(location).body(authorReturn);
    }

    @GetMapping
    public ResponseEntity<Page<ReturnAuthorDto>> getAllAuthors(@PageableDefault(sort = {"id"})Pageable pageable){
        Page<ReturnAuthorDto> authorReturn = authorService.getAllAuthors(pageable);
        return ResponseEntity.ok(authorReturn);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnAuthorDto> getAuthorById(@PathVariable Long id){
        ReturnAuthorDto authorReturn = authorService.getAuthorById(id);
        return ResponseEntity.ok(authorReturn);
    }

    @GetMapping("/{id}/livros")
    public ResponseEntity<List<ReturnBookDto>> getBooksByAuthor(@PathVariable Long id){
        List<ReturnBookDto> books = authorService.getBooksByAuthor(id);
        return ResponseEntity.ok(books);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ReturnAuthorDto> updateAuthor(@RequestBody @Valid CreateAuthorDto updateAuthor, @PathVariable Long id){
        ReturnAuthorDto authorReturn = authorService.updateAuthor(updateAuthor,id);
        return ResponseEntity.ok(authorReturn);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id){
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }


}
