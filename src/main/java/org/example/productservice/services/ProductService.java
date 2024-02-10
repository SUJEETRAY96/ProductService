package org.example.productservice.services;

import org.example.productservice.models.Product;


public interface ProductService {
    Product getProductById(Long id);
}
