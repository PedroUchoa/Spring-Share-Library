package com.example.digital_library.dtos;

import com.example.digital_library.infra.customValidation.ValidDateFormat;
import com.example.digital_library.models.Author;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record CreateAuthorDto(
        @NotBlank(message = "Name Can't be Empty")
        String name,
        @Email(message = "Email Format Invalid")
        @NotBlank(message = "Email Can't be Empty")
        String email,
        @NotNull(message = "BirthDay Can't be Null")
        @PastOrPresent(message = "The Birth Day cannot be in the future")
        @ValidDateFormat(pattern = "dd/MM/yyyy", message = "Date must be in dd/MM/yyyy format")
        LocalDate birthDay){

    public CreateAuthorDto(Author author) {
        this(author.getName(), author.getEmail(), author.getBirthDay());

    }
}