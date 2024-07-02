package com.taha.payment.notification;

import com.taha.payment.kafka.payment.PaymentConfirmation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification,String> {
}
