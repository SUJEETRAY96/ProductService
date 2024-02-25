package org.example.productservice.controllers;

import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Product;
import org.example.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    ProductService productService;

    @Test
    void getProductById() throws ProductNotFoundException {
        // arrange
        Product dummyProduct = new Product();
        dummyProduct.setId(1L);
        dummyProduct.setTitle("Dummy Product");
        when(productService.getProductById(1L)).thenReturn(dummyProduct);
        //act
        Product product = productService.getProductById(1L);
        //assert
        assertEquals(1, product.getId());
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void createProduct() {
    }

    @Test
    void deleteProductbyId() {
    }
}