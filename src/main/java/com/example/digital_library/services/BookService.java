package com.example.digital_library.services;

import com.example.digital_library.dtos.CreateBookDto;
import com.example.digital_library.dtos.ReturnBookDto;
import com.example.digital_library.models.Author;
import com.example.digital_library.models.Book;
import com.example.digital_library.models.Category;
import com.example.digital_library.repositories.AuthorRepository;
import com.example.digital_library.repositories.BookRepository;
import com.example.digital_library.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.PublicKey;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ReturnBookDto createBook(CreateBookDto createBookDto){
        Author author = authorRepository.getReferenceById(createBookDto.authorId());
        Category category = categoryRepository.getReferenceById(createBookDto.categoryId());

        Book book = bookRepository.save(new Book(createBookDto,author,category));
        return new ReturnBookDto(book);
    }

    public Page<ReturnBookDto> getAllBooks(Pageable pageable){
        return bookRepository.findAll(pageable).map(ReturnBookDto::new);
    }

    public ReturnBookDto getBookById(Long id){
        Book book = bookRepository.getReferenceById(id);
        return new ReturnBookDto(book);
    }

    public ReturnBookDto getBookByTitle(String title){
        Book book = bookRepository.getBookByTitle(title);
        return new ReturnBookDto(book);
    }

    public ReturnBookDto updateBook(CreateBookDto updateBookDto, Long id){
        Book book = bookRepository.getReferenceById(id);
        Author author = authorRepository.getReferenceById(updateBookDto.authorId());
        Category category = categoryRepository.getReferenceById(updateBookDto.categoryId());
        book.updateBook(updateBookDto,author,category);
        bookRepository.save(book);
        return new ReturnBookDto(book);
    }

    public void deleteBook(Long id){
        Book book = bookRepository.getReferenceById(id);
        bookRepository.delete(book);
    }





}
