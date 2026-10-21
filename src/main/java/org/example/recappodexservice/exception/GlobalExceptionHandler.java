package org.example.recappodexservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlePokemonNotFoundException
            (PokemonNotFoundException exception){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", exception.getMessage(),
                        "timestamp", LocalDateTime.now().toString()
                )
                );
    }
    @ExceptionHandler(CollectionNotFoundExpetion.class)
    public ResponseEntity<Map<String, Object>> handelCollectionNotFoundExpetion(CollectionNotFoundExpetion exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(Map.of(
                "message", exception.getMessage(),
                "timestamp", LocalDateTime.now().toString()
        )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
            MethodArgumentNotValidException exception) {

        String message = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("Validation failed");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                        "message", message,
                        "timestamp", LocalDateTime.now().toString()
                )
        );
    }
}
