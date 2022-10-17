package com.PI.API.repository;

import com.PI.API.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {

    @Query("FROM Category c WHERE c.title = ?1")
    Optional<Category> findByTitle(String title);

    //Category findByTitle(String title);
}
