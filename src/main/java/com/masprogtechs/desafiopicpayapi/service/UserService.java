package com.masprogtechs.desafiopicpayapi.service;

import com.masprogtechs.desafiopicpayapi.domain.User;
import com.masprogtechs.desafiopicpayapi.dtos.UserDTO;
import com.masprogtechs.desafiopicpayapi.enumetation.UserType;
import com.masprogtechs.desafiopicpayapi.exception.InsufficientBalanceException;
import com.masprogtechs.desafiopicpayapi.exception.InvalidTransferException;
import com.masprogtechs.desafiopicpayapi.exception.NotFoundException;
import com.masprogtechs.desafiopicpayapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void validateTransaction(User payer, BigDecimal amount){
       if(payer.getUserType() == UserType.LOJISTA){
           throw new InvalidTransferException("Lojistas só podem receber transferências");
       }

       if(payer.getBalance().compareTo(amount) < 0 ){
           throw new InsufficientBalanceException("Saldo insuficiente");
       }
    }

    public User findUserById(Long id){
      return this.userRepository.findById(id).orElseThrow(
              ()->  new NotFoundException("Usuário não encontrado!"));
    }

    public User createUser(UserDTO data){
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public void saveUser(User save){
        this.userRepository.save(save);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
