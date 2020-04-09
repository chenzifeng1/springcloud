package com.chenzifeng.springdemo.exception;

import com.chenzifeng.springdemo.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class BookExceptionController {

    private static Map<String, Book> bookRepo = new HashMap<>();

    @ExceptionHandler(value = BookNotFoundException.class)
    public ResponseEntity<Object> exception(BookNotFoundException exception){
        return new ResponseEntity<>("book not found", NOT_FOUND);
    }
}
