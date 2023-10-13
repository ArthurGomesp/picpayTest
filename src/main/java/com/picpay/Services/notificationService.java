package com.picpay.Services;

import com.picpay.domain.user.User;
import com.picpay.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.management.Notification;

@Service
public class notificationService {

    @Autowired
    private RestTemplate restTemplate;


    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://run.mocky.io/v3/8f5d42ef-ece4-477a-af7f-cd79afd7effd",notificationRequest, String.class);

        if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
            System.out.println("falha no envio da notificaçao");
            throw new Exception("Serviço de notifiçao esta fora do ar");
        }else {
            System.out.println("notificaçao enviada");
        }

    }
}
