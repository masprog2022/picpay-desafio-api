package com.masprogtechs.desafiopicpayapi.service;

import com.masprogtechs.desafiopicpayapi.domain.Transaction;
import com.masprogtechs.desafiopicpayapi.domain.User;
import com.masprogtechs.desafiopicpayapi.dtos.TransactionDTO;
import com.masprogtechs.desafiopicpayapi.exception.UnauthorizedException;
import com.masprogtechs.desafiopicpayapi.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private static final String AUTHORIZATION_SERVICE = "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc";

    private final UserService userService;
    private final TransactionRepository transactionRepository;
    private final RestTemplate restTemplate;
    private final NotificationService notificationService;

    public Transaction doTransaction(TransactionDTO transactionDTO){
        User payer = this.userService.findUserById(transactionDTO.getPayer());
        User payee = this.userService.findUserById(transactionDTO.getPayee());

        userService.validateTransaction(payer, transactionDTO.getValue());
        boolean isAuthorized = this.authorizeTransaction(payer, transactionDTO.getValue());
        if(!isAuthorized){
            throw new UnauthorizedException("Transferência não autorizada");
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.getValue());
        transaction.setPayer(payer);
        transaction.setPayee(payee);
        transaction.setTimestemp(LocalDateTime.now());

        payer.setBalance(payer.getBalance().subtract(transactionDTO.getValue()));
        payee.setBalance(payee.getBalance().add(transactionDTO.getValue()));

        this.transactionRepository.save(transaction);
        this.userService.saveUser(payer);
        this.userService.saveUser(payee);

        this.notificationService.sendNotification(payer, "Transação efectuada com sucesso!");
        this.notificationService.sendNotification(payee, "Transação recebida com sucesso!");

        return transaction;
    }

    public boolean authorizeTransaction(User payer, BigDecimal value){
      ResponseEntity<Map> authResponse = restTemplate.getForEntity(AUTHORIZATION_SERVICE, Map.class);
      if(authResponse.getStatusCode() == HttpStatus.OK ){
          String message = (String) requireNonNull(authResponse.getBody()).get("message");
          return "Autorizado".equalsIgnoreCase(message);
      }else return false;
    }

}
