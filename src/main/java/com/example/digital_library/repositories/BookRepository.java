package com.example.digital_library.repositories;

import com.example.digital_library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
    Book getBookByTitle(String title);
}
