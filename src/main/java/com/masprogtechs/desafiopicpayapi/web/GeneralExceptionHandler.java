package com.masprogtechs.desafiopicpayapi.web;

import com.masprogtechs.desafiopicpayapi.dtos.ExceptionDTO;
import com.masprogtechs.desafiopicpayapi.exception.InsufficientBalanceException;
import com.masprogtechs.desafiopicpayapi.exception.InvalidTransferException;
import com.masprogtechs.desafiopicpayapi.exception.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(InvalidTransferException.class)
    private ResponseEntity<Object> handleBadRequest(InvalidTransferException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> treatDuplicateEntry(DataIntegrityViolationException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Usuário já cadastrado", "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<Object> handleNotFound(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
