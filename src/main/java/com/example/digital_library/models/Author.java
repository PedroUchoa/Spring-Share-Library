package com.example.digital_library.models;

import com.example.digital_library.dtos.CreateAuthorDto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private LocalDate birthDay;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(Long id, String name, String email, List<Book> books, LocalDate birthDay) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.books = books;
        this.birthDay = birthDay;
    }

    public Author(CreateAuthorDto authorDto) {
        this.name = authorDto.name();
        this.email = authorDto.email();
        this.birthDay = authorDto.birthDay();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public void update(CreateAuthorDto createAuthorDto) {
        setEmail(createAuthorDto.email());
        setName(createAuthorDto.name());
        setBirthDay(createAuthorDto.birthDay());
    }
}
