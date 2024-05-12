package com.github.bifrurcated.hibernatesecondlevelcache.repository;

import com.github.bifrurcated.hibernatesecondlevelcache.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
