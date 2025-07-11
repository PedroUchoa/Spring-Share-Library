package com.example.digital_library.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Author not found in the database")
public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(Long id){
        super("Author with id: " + id + " Not Found");
    }
}
