package com.jamal.ecommerce_project.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jamal.ecommerce_project.model.Product;
import com.jamal.ecommerce_project.dto.ProductDTO; // 🟢 ADDED: Import your new lightweight DTO layout

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    // 🟢 ADDED: Custom optimized query that drops the heavy image binary array entirely
    @Query("SELECT p.id as id, p.name as name, p.brand as brand, p.description as description, " +
           "p.price as price, p.category as category, p.stockQuantity as stockQuantity, " +
           "p.releaseDate as releaseDate, p.productAvailable as productAvailable FROM Product p")
    List<ProductDTO> findAllWithoutImageData();

    // Your existing search query remains completely untouched below
    @Query("SELECT p FROM Product p WHERE " +
           "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchProducts(String keyword);
}
