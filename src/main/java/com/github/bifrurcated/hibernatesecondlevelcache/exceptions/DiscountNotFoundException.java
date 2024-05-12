package com.github.bifrurcated.hibernatesecondlevelcache.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DiscountNotFoundException extends ResponseStatusException {
    public DiscountNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Product not found");
    }
}
