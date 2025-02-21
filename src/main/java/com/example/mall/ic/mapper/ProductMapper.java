package com.example.mall.ic.mapper;

import com.example.mall.ic.model.Product;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ProductMapper {
    int insert(Product product);
    int update(Product product);
    int deleteById(Long id);
    Product selectById(Long id);
    List<Product> selectList();
} 