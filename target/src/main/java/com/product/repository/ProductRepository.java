package com.product.repository;

import com.product.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
}

