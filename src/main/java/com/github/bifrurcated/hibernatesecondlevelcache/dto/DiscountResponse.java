package com.github.bifrurcated.hibernatesecondlevelcache.dto;

import com.github.bifrurcated.hibernatesecondlevelcache.entity.DiscountSegment;

import java.io.Serializable;

/**
 * DTO for {@link DiscountSegment}
 */
public record DiscountResponse(Integer discount) implements Serializable {
}