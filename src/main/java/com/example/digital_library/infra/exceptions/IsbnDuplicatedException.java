package com.example.digital_library.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "ISBN Already exist in the database")
public class IsbnDuplicatedException extends RuntimeException{

    public IsbnDuplicatedException(String isbn){
        super("Isbn: " + isbn + " already exist in the database");
    }

}
