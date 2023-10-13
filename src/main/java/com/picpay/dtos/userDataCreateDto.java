package com.picpay.dtos;

import com.picpay.domain.user.userType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record userDataCreateDto(
        @NotBlank
        String name,
        @NotBlank
        String lastName,
        @NotBlank
        String document,
        @NotBlank
        String email,
        @NotBlank
        String password,
        @NotNull
        BigDecimal balance,
        userType userType
) {
}
