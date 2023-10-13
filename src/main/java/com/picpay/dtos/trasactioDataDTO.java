package com.picpay.dtos;

import java.math.BigDecimal;

public record trasactioDataDTO(
        BigDecimal amount,
        String documentSender,
        String documentReciver
) {
}
