package com.tutorial.crudspringbootmongoback.global.exceptions;

import com.tutorial.crudspringbootmongoback.global.dto.MessageDto;
import com.tutorial.crudspringbootmongoback.global.utils.Operations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<MessageDto> throwNotFoundException(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageDto(HttpStatus.NOT_FOUND,e.getMessage()));
    }
    @ExceptionHandler(AttibuteException.class)
    public ResponseEntity<MessageDto> throwBadRequestException(AttibuteException e){
        return ResponseEntity.badRequest()
                .body(new MessageDto(HttpStatus.BAD_REQUEST,e.getMessage()));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageDto> globalException(Exception e){
        return ResponseEntity.internalServerError()
                .body(new MessageDto(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageDto> validationAttributeException(MethodArgumentNotValidException e){
        List<String> message = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach( (err) -> {
            message.add(err.getDefaultMessage());
        });
        return ResponseEntity.badRequest()
                .body(new MessageDto(HttpStatus.BAD_REQUEST, Operations.trimBrackets(message.toString())));
    }
}
