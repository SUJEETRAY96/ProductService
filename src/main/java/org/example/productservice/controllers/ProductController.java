package org.example.productservice.controllers;

import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Product;
import org.example.productservice.security.services.AuthenticationService;
import org.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private AuthenticationService authenticationService;

    @Autowired
    public ProductController(@Qualifier("SelfProductService") ProductService productService,
                             AuthenticationService authenticationService){
        this.productService = productService;
        this.authenticationService = authenticationService;
    }
    @GetMapping("/{id}")
    public Product getProductById(@RequestHeader("token") String token,
                                  @PathVariable("id") Long id) throws ProductNotFoundException, AccessDeniedException {
        if(!authenticationService.authenticateUser(token)){
            throw new AccessDeniedException("User is not authenticated to access ");
        }
        return productService.getProductById(id);
    }
    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProductbyId(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.deleteProductById(id);
    }
}
