package org.example.productservice.controllers.advices;

import org.example.productservice.dtos.ExceptionDTO;
import org.example.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    ResponseEntity<ExceptionDTO> handleProductNotFoundException(ProductNotFoundException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(e.getMessage());
        exceptionDTO.setStatus("Failure");
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }
}
