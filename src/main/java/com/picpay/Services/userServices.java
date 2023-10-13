package com.picpay.Services;

import com.picpay.dtos.userDataCreateDto;
import com.picpay.domain.user.User;
import com.picpay.repositories.userRepository;
import com.picpay.domain.user.userType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Map;

@Service
public class userServices {
    @Autowired
    private userRepository repository;

    @Autowired
    private RestTemplate restTemplate;




    public Boolean validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getBalance().compareTo(amount) >= 0 && sender.getUserType() == userType.COMMON) {

            if (getVerification()){

                if (timeRestriction()) {
                    BigDecimal limitTransfarece = new BigDecimal("3500");
                    if (amount.compareTo(limitTransfarece) > 0) {
                        throw new Exception("Apos as 22:00 horas so é permitido transfercias de ate R$3.500");
                    } else {
                        return true;
                    }
                } else {
                    return true;

                }
            }else {
                return false;
            }

        } else {
            if (sender.getBalance().compareTo(amount) <= 0) {
                throw new Exception("Saldo insuficiente");

            } else if (sender.getUserType() == userType.MERCHANT) {
                throw new Exception("Usuarios do tipo lojista não estão autorizados a realizar transferências.");
            }
            return false;
        }
    }


    public Boolean timeRestriction() {
        LocalTime currentTime = LocalTime.now();
        LocalTime strarInterval = LocalTime.of(22, 0);  // Por exemplo, 09:00
        LocalTime endInterval = LocalTime.of(05, 0);   // Por exemplo, 17:00

        if (currentTime.isAfter(strarInterval) && currentTime.isBefore(endInterval)) {
            return true;
        } else {
            return false;
        }
    }

    public User findUserById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public ResponseEntity createUser(userDataCreateDto userData) {
        User user = new User(userData);
        repository.save(user);
        if (repository.findById(user.getId()).isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    public Boolean getVerification(){
        ResponseEntity<Map> autorisationRespose = restTemplate.getForEntity("https://run.mocky.io/v3/8f5d42ef-ece4-477a-af7f-cd79afd7effd", Map.class);

        if (autorisationRespose.getStatusCode() == HttpStatus.OK){
            return true;
        }else {
            return false;
        }

    }


}
