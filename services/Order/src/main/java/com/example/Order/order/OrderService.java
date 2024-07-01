package com.example.Order.order;


import com.example.Order.customer.CustomerClient;
import com.example.Order.exception.BusinessException;
import com.example.Order.kafka.OrderConfirmation;
import com.example.Order.kafka.OrderProducer;
import com.example.Order.orderline.OrderLineRequest;
import com.example.Order.orderline.OrderLineService;
import com.example.Order.product.ProductClient;
import com.example.Order.product.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;


    public Integer createOrder(OrderRequest request) {
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Customer not found"));

        // purchase the product --> product-ms (RestTemplate)
        var purchaseProducts = this.productClient.purchaseProducts(request.products());
        //persist the order
        var order = this.repository.save(mapper.toOrder(request));
        //persist the orderline
        for(PurchaseRequest purchaseRequest: request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseProducts

                )
        );


        return order.getId();

    }
}
