package com.praharsh.CafeManagement.repositries;

import com.praharsh.CafeManagement.dtos.ProductDto;
import com.praharsh.CafeManagement.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product,Long> {
    List<Product> findAllByCategoryId(Long categoryId);

    List<Product> findAllByCategoryIdAndNameContaining(Long categoryId, String title);
}
