package com.example.mall.ic.service;

import com.example.mall.ic.model.Product;
import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Long id);
    Product getProduct(Long id);
    List<Product> listProducts();
} 