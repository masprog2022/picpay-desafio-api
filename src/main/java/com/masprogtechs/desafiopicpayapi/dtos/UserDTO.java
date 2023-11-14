package com.masprogtechs.desafiopicpayapi.dtos;

import com.masprogtechs.desafiopicpayapi.enumetation.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String fullName;
    private String cpf;
    private String email;
    private UserType userType;
    private BigDecimal balance;
}
