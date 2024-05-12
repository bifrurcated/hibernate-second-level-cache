package com.github.bifrurcated.hibernatesecondlevelcache.service;

import com.github.bifrurcated.hibernatesecondlevelcache.entity.Category;
import com.github.bifrurcated.hibernatesecondlevelcache.entity.Product;
import com.github.bifrurcated.hibernatesecondlevelcache.exceptions.CategoryNotFoundException;
import com.github.bifrurcated.hibernatesecondlevelcache.repository.CategoryRepository;
import com.github.bifrurcated.hibernatesecondlevelcache.repository.ProductRepository;
import com.github.bifrurcated.hibernatesecondlevelcache.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void addProduct(String name, BigDecimal price, Integer count, Long categoryId) {
        var category = categoryRepository.findById(categoryId)
            .orElseThrow(CategoryNotFoundException::new);
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCount(count);
        product.setCategory(category);
        productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
}
