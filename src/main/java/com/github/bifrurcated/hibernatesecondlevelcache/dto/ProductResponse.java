package com.github.bifrurcated.hibernatesecondlevelcache.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ProductResponse(
    Long id,
    String name,
    BigDecimal price,
    Integer count,
    @JsonProperty("category_name") String categoryName
) {
}
