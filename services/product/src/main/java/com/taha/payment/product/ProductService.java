package com.taha.payment.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;
    public Integer createProduct(ProductRequest request) {
        return null;
    }

    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> request) {
        return null;
    }

    public ProductResponse findById(Integer productId) {
        return null;
    }

    public List<ProductResponse> findAll() {
        return null;
    }
}
