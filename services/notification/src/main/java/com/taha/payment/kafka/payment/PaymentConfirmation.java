package com.taha.payment.kafka.payment;

import java.math.BigDecimal;

public record PaymentConfirmation (
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
){
}
