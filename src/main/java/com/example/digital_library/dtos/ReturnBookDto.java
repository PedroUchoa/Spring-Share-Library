package com.example.digital_library.dtos;

import com.example.digital_library.models.Author;
import com.example.digital_library.models.Book;
import com.example.digital_library.models.Category;

import java.math.BigDecimal;

public record ReturnBookDto(Long id, String title, String isbn, Integer publicationYear, BigDecimal price, Author author, Category category) {

    public ReturnBookDto(Book book) {
        this(book.getId(), book.getTitle(), book.getIsbn(), book.getPublicationYear(),book.getPrice() ,book.getAuthor(), book.getCategory());
    }
}
