package com.github.bifrurcated.hibernatesecondlevelcache.service;

import com.github.bifrurcated.hibernatesecondlevelcache.entity.DiscountSegment;

public interface DiscountService {

    void addDiscount(Long productId, Long LocationId, Integer discount);

    Integer getProductDiscount(Long productId, Long locationId) ;

    void updateDiscount(Long productId, Long LocationId, Integer discount);

    void deleteDiscount(Long productId, Long LocationId);
}
