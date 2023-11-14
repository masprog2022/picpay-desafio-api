package com.masprogtechs.desafiopicpayapi.web;

import com.masprogtechs.desafiopicpayapi.domain.Transaction;
import com.masprogtechs.desafiopicpayapi.dtos.TransactionDTO;
import com.masprogtechs.desafiopicpayapi.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
@Tag(name = "Transação", description = "Endpoints para gerenciar transação" )
public class TransactionController {

    private final TransactionService transactionService;
    @Operation(summary = "Efectuar transação", description = "Efectuar transação",
            responses = {
                    @ApiResponse(responseCode = "201", description = "transação efectuada com sucesso",
                            content = @Content(mediaType = "application/json"))
            })
    @PostMapping
    public ResponseEntity<Transaction> doTransaction(@RequestBody TransactionDTO transaction){
        Transaction newTransaction = this.transactionService.doTransaction(transaction);
        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }
}
