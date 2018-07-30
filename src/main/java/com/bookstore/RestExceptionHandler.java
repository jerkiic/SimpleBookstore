package com.bookstore;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bookstore.exception.BookNotFoundException;
import com.bookstore.exception.UserNotFoundException;

@Controller
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
 
    @ExceptionHandler({ UserNotFoundException.class })
    protected ResponseEntity<Object> handleUserNotFound(
      Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "User not found", 
          new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
 
    @ExceptionHandler({ BookNotFoundException.class })
    protected ResponseEntity<Object> handleBookNotFound(
      Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Book not found", 
          new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}