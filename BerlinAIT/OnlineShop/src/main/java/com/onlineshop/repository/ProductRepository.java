package com.onlineshop.repository;

import com.onlineshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM products p WHERE p.category.categoryId = :categoryId")
    List<Product> findByCategoryId(@Param("categoryId") Integer categoryId);

    List<Product> findByDescriptionLikeIgnoreCase(String likePattern);
}
