package com.example.Order.kafka;

import com.example.Order.customer.CustomerResponse;
import com.example.Order.order.PaymentMethod;
import com.example.Order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
){
}
