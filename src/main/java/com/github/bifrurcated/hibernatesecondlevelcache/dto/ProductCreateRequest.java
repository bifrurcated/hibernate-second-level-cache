package com.github.bifrurcated.hibernatesecondlevelcache.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ProductCreateRequest(
    String name,
    BigDecimal price,
    Integer count,
    @JsonProperty("category_id") Long categoryId
) {
}
