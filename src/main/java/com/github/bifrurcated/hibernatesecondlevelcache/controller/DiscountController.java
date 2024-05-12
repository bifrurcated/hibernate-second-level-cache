package com.github.bifrurcated.hibernatesecondlevelcache.controller;

import com.github.bifrurcated.hibernatesecondlevelcache.dto.DiscountRequest;
import com.github.bifrurcated.hibernatesecondlevelcache.dto.DiscountResponse;
import com.github.bifrurcated.hibernatesecondlevelcache.service.DiscountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/discounts")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping(params = {"productId", "locationId"})
    public DiscountResponse getProductDiscounts(@RequestParam("productId") Long productId,
                                                @RequestParam("locationId") Long locationId) {
        var discount = discountService.getProductDiscount(productId, locationId);
        return mapper.map(discount, DiscountResponse.class);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addDiscount(@RequestBody DiscountRequest request) {
        discountService.addDiscount(
            request.productId(),
            request.locationId(),
            request.discount());
    }

    @PatchMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateDiscount(@RequestBody DiscountRequest request) {
        discountService.updateDiscount(
            request.productId(),
            request.locationId(),
            request.discount());
    }

    @DeleteMapping(params = {"productId", "locationId"})
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void removeDiscount(@RequestParam("productId") Long productId,
                               @RequestParam("locationId") Long locationId) {
        discountService.deleteDiscount(productId, locationId);
    }
}
