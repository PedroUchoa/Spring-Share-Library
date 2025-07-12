package com.example.digital_library.services;

import com.example.digital_library.dtos.CreateBookDto;
import com.example.digital_library.dtos.ReturnBookDto;
import com.example.digital_library.dtos.ImportBookDto;
import com.example.digital_library.infra.exceptions.*;
import com.example.digital_library.models.Author;
import com.example.digital_library.models.Book;
import com.example.digital_library.models.Category;
import com.example.digital_library.repositories.AuthorRepository;
import com.example.digital_library.repositories.BookRepository;
import com.example.digital_library.repositories.CategoryRepository;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ReturnBookDto createBook(CreateBookDto createBookDto){
        Author author = authorRepository.findById(createBookDto.authorId()).orElseThrow(()->new AuthorNotFoundException(createBookDto.authorId()));
        Category category = categoryRepository.findById(createBookDto.categoryId()).orElseThrow(()->new CategoryNotFoundException(createBookDto.categoryId()));
        if(bookRepository.getBookByIsbn(createBookDto.isbn())!=null) throw new IsbnDuplicatedException(createBookDto.isbn());
        Book book = bookRepository.save(new Book(createBookDto,author,category));
        return new ReturnBookDto(book);
    }

    public ReturnBookDto importBook(ImportBookDto importedBook){
        Author author = authorRepository.findById(importedBook.authorId()).orElseThrow(()->new AuthorNotFoundException(importedBook.authorId()));
        Category category = categoryRepository.findById(importedBook.categoryId()).orElseThrow(()->new CategoryNotFoundException(importedBook.categoryId()));
        Book book = parseWebValues(importedBook.url(), author,category);
        if(bookRepository.getBookByIsbn(book.getIsbn()) != null) throw new IsbnDuplicatedException(book.getIsbn());
        Book returnBook = bookRepository.save(book);
        return new ReturnBookDto(returnBook);
    }

    public Page<ReturnBookDto> getAllBooks(Pageable pageable){
        return bookRepository.findAll(pageable).map(ReturnBookDto::new);
    }

    public ReturnBookDto getBookById(Long id){
        Book book = bookRepository.findById(id).orElseThrow(()->new BookNotFoundException(id));
        return new ReturnBookDto(book);
    }

    public ReturnBookDto getBookByTitle(String title){
        Book book = bookRepository.getBookByTitle(title);
        if(book == null) throw new BookNotFoundException(null);
        return new ReturnBookDto(book);
    }

    public ReturnBookDto updateBook(CreateBookDto updateBookDto, Long id){
        Book book = bookRepository.findById(id).orElseThrow((() -> new BookNotFoundException(id)));
        Author author = authorRepository.getReferenceById(updateBookDto.authorId());
        Category category = categoryRepository.getReferenceById(updateBookDto.categoryId());
        book.updateBook(updateBookDto,author,category);
        bookRepository.save(book);
        return new ReturnBookDto(book);
    }

    public void deleteBook(Long id){
        Book book = bookRepository.findById(id).orElseThrow(()->new BookNotFoundException(id));
        bookRepository.delete(book);
    }


    private static Book parseWebValues(String url, Author author, Category category){
        try(WebClient client = new WebClient()){
            client.getOptions().setJavaScriptEnabled(false);
            client.getOptions().setCssEnabled(false);
            HtmlPage searchPage = client.getPage(url);
            HtmlElement priceWhole = (HtmlSpan) searchPage.getByXPath("//span[@class='a-price-whole']").get(0);
            HtmlElement priceFraction = (HtmlSpan) searchPage.getByXPath("//span[@class='a-price-fraction']").get(0);
            HtmlElement title =  (HtmlSpan) searchPage.getElementById("productTitle");
            HtmlElement yearPublication = (HtmlSpan) searchPage.getByXPath("//div[@id='detailBullets_feature_div']//span[contains(text(),'Data da publicação')]").get(0);
            HtmlElement isbn = (HtmlSpan) searchPage.getByXPath("//div[@id='detailBullets_feature_div']//span[contains(text(),'ISBN-13')]").get(0);

            BigDecimal formatedPrice = new BigDecimal(priceWhole.getTextContent().replaceAll(",",".")+priceFraction.getTextContent());
            Integer formatedYear = Integer.valueOf((yearPublication.getNextElementSibling().getTextContent().substring(yearPublication.getNextElementSibling().getTextContent().length()-4)));

            return new Book(title.getTextContent().substring(2),formatedPrice,formatedYear,isbn.getNextElementSibling().getTextContent().replaceAll("-",""),author,category);

        }catch (Exception e){
            throw new UrlProblemException();
        }

    }


}