package com.github.bifrurcated.hibernatesecondlevelcache.service;

import com.github.bifrurcated.hibernatesecondlevelcache.entity.DiscountSegment;
import com.github.bifrurcated.hibernatesecondlevelcache.exceptions.DiscountNotFoundException;
import com.github.bifrurcated.hibernatesecondlevelcache.exceptions.LocationNotFoundException;
import com.github.bifrurcated.hibernatesecondlevelcache.exceptions.ProductNotFoundException;
import com.github.bifrurcated.hibernatesecondlevelcache.repository.DiscountSegmentRepository;
import com.github.bifrurcated.hibernatesecondlevelcache.repository.LocationRepository;
import com.github.bifrurcated.hibernatesecondlevelcache.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountSegmentRepository discountRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    @Transactional
    public void addDiscount(Long productId, Long locationId, Integer discount) {
        var product = productRepository.findById(productId)
            .orElseThrow(ProductNotFoundException::new);
        var location = locationRepository.findById(locationId)
            .orElseThrow(LocationNotFoundException::new);
        DiscountSegment discountSegment = new DiscountSegment();
        discountSegment.setProduct(product);
        discountSegment.setLocation(location);
        discountSegment.setDiscount(discount);
        discountRepository.save(discountSegment);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getProductDiscount(Long productId, Long locationId) {
        return discountRepository.findByProductIdAndLocationId(productId, locationId)
            .orElseThrow(DiscountNotFoundException::new)
            .getDiscount();
    }

    @Override
    @Transactional
    public void updateDiscount(Long productId, Long locationId, Integer discount) {
        DiscountSegment discountSegment = discountRepository.findByProductIdAndLocationId(productId, locationId)
            .orElseThrow(DiscountNotFoundException::new);
        discountSegment.setDiscount(discount);
        discountRepository.save(discountSegment);
    }

    @Override
    @Transactional
    public void deleteDiscount(Long productId, Long locationId) {
        discountRepository.deleteByProductIdAndLocationId(productId, locationId);
    }
}
