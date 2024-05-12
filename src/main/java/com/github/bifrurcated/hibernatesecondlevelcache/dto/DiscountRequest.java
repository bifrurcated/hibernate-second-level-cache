package com.github.bifrurcated.hibernatesecondlevelcache.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DiscountRequest(
    @JsonProperty("product_id") Long productId,
    @JsonProperty("location_id") Long locationId,
    Integer discount) {
}
