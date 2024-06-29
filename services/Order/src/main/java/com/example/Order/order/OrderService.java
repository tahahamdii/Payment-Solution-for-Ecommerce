package com.example.Order.order;


import com.example.Order.customer.CustomerClient;
import com.example.Order.exception.BusinessException;
import com.example.Order.product.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    public Integer createOrder(OrderRequest request) {
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Customer not found"));

        // purchase the product --> product-ms (RestTemplate)


    }
}
