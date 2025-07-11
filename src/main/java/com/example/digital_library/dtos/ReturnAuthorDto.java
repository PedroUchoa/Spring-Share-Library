package com.example.digital_library.dtos;

import com.example.digital_library.models.Author;

import java.time.LocalDate;

public record ReturnAuthorDto(Long id, String name, String email, LocalDate birthDay) {

    public ReturnAuthorDto(Author author) {
        this(author.getId(), author.getName(), author.getEmail(), author.getBirthDay());
    }
}
