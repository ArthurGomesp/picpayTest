package com.picpay.dtos;

import com.picpay.domain.user.User;
import com.picpay.domain.user.userType;


import java.math.BigDecimal;

public record DataDetailsUser(Long id, String name, String lastName, String document, String email, BigDecimal balance, userType userType) {
    public DataDetailsUser(User user){
        this(user.getId(), user.getName(), user.getLastName(), user.getDocument(), user.getEmail(), user.getBalance(), user.getUserType());
    }

}
