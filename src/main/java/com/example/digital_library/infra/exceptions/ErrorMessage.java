package com.example.digital_library.infra.exceptions;

import org.springframework.http.HttpStatus;

public record ErrorMessage(HttpStatus status, String Message) {
}
