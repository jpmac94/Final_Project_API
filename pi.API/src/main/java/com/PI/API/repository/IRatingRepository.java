package com.PI.API.repository;

import com.PI.API.model.Product;
import com.PI.API.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IRatingRepository extends JpaRepository<Rating,Long> {

    @Query("FROM Rating r WHERE r.product.id = ?1")
    Set<Rating> findRatingByProduct(Long id);
}
