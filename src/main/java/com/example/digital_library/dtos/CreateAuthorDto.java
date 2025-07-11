package com.example.digital_library.dtos;

import com.example.digital_library.models.Author;

import java.time.LocalDate;

public record CreateAuthorDto(
        String name,
        String email,
        LocalDate birthDay){

    public CreateAuthorDto(Author author) {
        this(author.getName(), author.getEmail(), author.getBirthDay());

    }
}