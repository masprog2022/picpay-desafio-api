package com.masprogtechs.desafiopicpayapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.masprogtechs.desafiopicpayapi.dtos.UserDTO;
import com.masprogtechs.desafiopicpayapi.enumetation.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private BigDecimal balance;


    public User(UserDTO data) {
        this.fullName = data.getFullName();
        this.cpf = data.getCpf();
        this.balance = data.getBalance();
        this.userType = data.getUserType();
        this.email = data.getEmail();
    }
}
