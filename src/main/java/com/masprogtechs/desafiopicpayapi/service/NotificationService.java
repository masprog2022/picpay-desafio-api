package com.masprogtechs.desafiopicpayapi.service;

import com.masprogtechs.desafiopicpayapi.domain.User;
import com.masprogtechs.desafiopicpayapi.dtos.NotificationDTO;
import com.masprogtechs.desafiopicpayapi.exception.NotificationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private static final String NOTIFICATION = "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6";

    private final RestTemplate restTemplate;

    public void sendNotification(User user, String message){
        String email = user.getEmail();
        NotificationDTO notificationEmail = new NotificationDTO(email, message);
       ResponseEntity<String> notificationResponse = restTemplate.postForEntity(NOTIFICATION, notificationEmail, String.class);

       if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
         throw new NotificationException("O serviço de notificação está indisponível.");
       }

    }
}
