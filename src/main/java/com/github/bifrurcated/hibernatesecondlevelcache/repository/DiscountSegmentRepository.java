package com.github.bifrurcated.hibernatesecondlevelcache.repository;

import com.github.bifrurcated.hibernatesecondlevelcache.entity.DiscountSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DiscountSegmentRepository extends JpaRepository<DiscountSegment, Long> {

    @Query(value = """
        SELECT ds.discount
        FROM DiscountSegment ds
        WHERE ds.product.id = :productId AND ds.location.id = :locationId
    """)
    Optional<DiscountSegment> findByProductIdAndLocationId(Long productId, Long locationId);

    @Modifying
    @Query(value = """
        DELETE FROM DiscountSegment ds
        WHERE ds.product.id = :productId AND ds.location.id = :locationId
    """)
    void deleteByProductIdAndLocationId(Long productId, Long locationId);
}
