package com.taha.payment.kafka;

import com.taha.payment.kafka.order.OrderConfirmation;
import com.taha.payment.kafka.payment.PaymentConfirmation;
import com.taha.payment.notification.Notification;
import com.taha.payment.notification.NotificationRepository;
import com.taha.payment.notification.NotificationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository repository;
    // private final Email Service emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) {
        log.info(format("Consuming to the message from payment topic:: %s",paymentConfirmation));
        repository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        // todo send email
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) {
        log.info(format("Consuming to the message from order topic:: %s",orderConfirmation));
        repository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        // todo send email
    }
}
