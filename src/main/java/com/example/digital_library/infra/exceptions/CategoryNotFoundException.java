package com.example.digital_library.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Category not found in the database")
public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(Long id){
        super("Category with id: " + id + " Not Found");
    }
}
