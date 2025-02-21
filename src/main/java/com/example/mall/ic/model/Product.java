package com.example.mall.ic.model;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Product {
    private Long id;
    private String name;
    private String code;
    private BigDecimal price;
    private Integer stock;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
} 