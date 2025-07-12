package com.example.digital_library.repositories;

import com.example.digital_library.models.Book;
import jdk.dynalink.linker.support.Guards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    Book getBookByTitle(String title);

    Book getBookByIsbn(String isbn);
}
