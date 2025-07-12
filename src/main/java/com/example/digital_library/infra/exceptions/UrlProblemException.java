package com.example.digital_library.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Some Problem With The WebSite You're Trying To Access, Please Chek The URL And Try Again!")
public class UrlProblemException extends RuntimeException{

    public UrlProblemException(){
        super("Some Problem With The WebSite You're Trying To Access, Please Chek The URL And Try Again!");
    }

}
