package com.zbank.credit_card_application.dto;

public record CreditCheckResponse(
        String applicationNo,
        String customerName,
        Integer creditScore,
        String scoreSource,
        String message
) {
}
