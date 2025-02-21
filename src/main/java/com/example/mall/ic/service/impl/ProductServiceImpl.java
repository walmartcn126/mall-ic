package com.example.mall.ic.service.impl;

import com.example.mall.ic.mapper.ProductMapper;
import com.example.mall.ic.model.Product;
import com.example.mall.ic.service.ProductService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    
    private final ProductMapper productMapper;

    @Override
    public Product createProduct(Product product) {
        productMapper.insert(product);
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        productMapper.update(product);
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        productMapper.deleteById(id);
    }

    @Override
    public Product getProduct(Long id) {
        return productMapper.selectById(id);
    }

    @Override
    public List<Product> listProducts() {
        return productMapper.selectList();
    }
} 