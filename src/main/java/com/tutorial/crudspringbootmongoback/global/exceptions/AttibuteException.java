package com.tutorial.crudspringbootmongoback.global.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AttibuteException extends Exception{
    public AttibuteException(String message){
        super(message);
    }
}
