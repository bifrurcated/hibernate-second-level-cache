package com.github.bifrurcated.hibernatesecondlevelcache.service;

import com.github.bifrurcated.hibernatesecondlevelcache.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void addProduct(String name, BigDecimal price, Integer count, Long categoryId);

    Product findProductById(Long id);

    void deleteProductById(Long id);

    void updateProduct(Product product);

    List<Product> findProductsByCategoryId(Long categoryId);
}
