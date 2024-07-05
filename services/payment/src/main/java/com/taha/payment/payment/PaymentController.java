package com.taha.payment.payment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public ResponseEntity<Integer> createPayment(
            @RequestBody @Valid PaymentRequest request
    ) {
        return ResponseEntity.ok(this.service.createPayment(request));
    }
    @GetMapping
    public ResponseEntity<List<Payment>> getPayments() {

    }
    @PostMapping
    public ResponseEntity<Payment> updatePayment(

    )
    {}
    @PostMapping
    public ResponseEntity<Payment> getPayment(

    ){}
}