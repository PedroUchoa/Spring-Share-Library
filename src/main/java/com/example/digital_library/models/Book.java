package com.example.digital_library.models;


import jakarta.persistence.*;

import java.math.BigDecimal;

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
    private Author author;
    private Category category;

}
