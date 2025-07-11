package com.example.digital_library.models;


import com.example.digital_library.dtos.CreateBookDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "tb_books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String isbn;
    private Integer publicationYear;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    @JsonManagedReference
    private Author author;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonManagedReference
    private Category category;

    public Book() {
    }

    public Book(Long id, String isbn, String title, Integer publicationYear, BigDecimal price, Author author, Category category) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.publicationYear = publicationYear;
        this.price = price;
        this.author = author;
        this.category = category;
    }

    public Book(CreateBookDto createBookDto, Author author, Category category) {
        this.title = createBookDto.title();
        this.isbn = createBookDto.isbn();
        this.publicationYear = createBookDto.publicationYear();
        this.price = createBookDto.price();
        this.author = author;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


    public void updateBook(CreateBookDto updateBookDto, Author author, Category category) {
        setTitle(updateBookDto.title());
        setIsbn(updateBookDto.isbn());
        setPublicationYear(updateBookDto.publicationYear());
        setPrice(updateBookDto.price());
        setAuthor(author);
        setCategory(category);
    }
}
