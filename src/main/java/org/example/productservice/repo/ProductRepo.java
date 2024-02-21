package org.example.productservice.repo;

import org.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Optional<Product> findById(Long aLong);


    Product save(Product product);
}
