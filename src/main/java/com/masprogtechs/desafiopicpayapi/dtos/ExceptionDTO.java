package com.masprogtechs.desafiopicpayapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDTO {
    private String message;
    private String status;

}
