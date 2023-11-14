package com.masprogtechs.desafiopicpayapi.exception;

public class InvalidTransferException extends RuntimeException {
    public InvalidTransferException(String message){
        super(message);
    }
}
