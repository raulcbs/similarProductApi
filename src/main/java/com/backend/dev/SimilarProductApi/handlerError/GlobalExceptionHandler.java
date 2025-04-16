package com.backend.dev.SimilarProductApi.handlerError;

import com.backend.dev.SimilarProductApi.dto.ErrorDTO;
import com.backend.dev.SimilarProductApi.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundException(ProductNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDTO().setMessage(ex.getMessage()));
    }

    @ExceptionHandler(WebClientResponseException.NotFound.class)
    public ResponseEntity<ErrorDTO> handleWebClientNotFoundException(WebClientResponseException.NotFound ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDTO().setMessage("Product Not found"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGenericException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO().setMessage("Internal server error"));
    }
}