package com.github.bifrurcated.hibernatesecondlevelcache.controller;

import com.github.bifrurcated.hibernatesecondlevelcache.dto.ProductCreateRequest;
import com.github.bifrurcated.hibernatesecondlevelcache.dto.ProductResponse;
import com.github.bifrurcated.hibernatesecondlevelcache.dto.ProductUpdateRequest;
import com.github.bifrurcated.hibernatesecondlevelcache.entity.Product;
import com.github.bifrurcated.hibernatesecondlevelcache.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable("id") Long id) {
        var product = productService.findProductById(id);
        return mapper.map(product, ProductResponse.class);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addProduct(@RequestBody ProductCreateRequest request) {
        productService.addProduct(
            request.name(),
            request.price(),
            request.count(),
            request.categoryId());
    }

    @PatchMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateProduct(@RequestBody ProductUpdateRequest request) {
        var product = mapper.map(request, Product.class);
        productService.updateProduct(product);
    }

    @GetMapping(params = "categoryId")
    public List<ProductResponse> getProducts(@RequestParam("categoryId") Long categoryId) {
        var products = productService.findProductsByCategoryId(categoryId);
        return products.stream()
           .map(product -> mapper.map(product, ProductResponse.class))
           .toList();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void removeProduct(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
    }
}
