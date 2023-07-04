package com.example.links_converter_api.handler;

import com.example.links_converter_api.exception.NullLongLinkException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LinkServiceExceptionHandler {



    //TODO while long link not found by short link - return 404 error response
    // (catch Throwable) while another exception situation - return 500 error with custom message like "Something went wrong! Try again later"
    // Send full stack trace from or default exception message to client - bad iea
    // whe should send custom message like a 404 (long link not fount by short link) - "Long link not found! You should recreate short link"

    @ExceptionHandler(NullLongLinkException.class)
    public ResponseEntity<String> handleNullLinkException(NullLongLinkException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleAllExceptions(Throwable e){
        String message = "Something went wrong! Try again later";
        return ResponseEntity.status(500).body(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleNotValidRequestException(MethodArgumentNotValidException ex){
      return ResponseEntity.status(400).body(ex.getMessage());
    };
}
