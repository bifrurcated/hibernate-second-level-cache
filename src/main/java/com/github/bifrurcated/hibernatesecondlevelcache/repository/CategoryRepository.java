package com.github.bifrurcated.hibernatesecondlevelcache.repository;

import com.github.bifrurcated.hibernatesecondlevelcache.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
