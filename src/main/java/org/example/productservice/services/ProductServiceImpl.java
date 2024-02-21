package org.example.productservice.services;

import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.repo.CategoryRepo;
import org.example.productservice.repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
public class ProductServiceImpl implements ProductService{

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    public ProductServiceImpl(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepo.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException("Product is not found with id :"+id);
        }
        return product.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product deleteProductById(Long id) {
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        Optional<Category> categoryOptional = categoryRepo.findByName(product.getCategory().getName());
        Category category = new Category();
        category.setName(product.getCategory().getName());
        if(categoryOptional.isEmpty()){
            product.setCategory(categoryRepo.save(category));
        }
        return productRepo.save(product);
    }

    @Override
    public void updateProductById() {

    }
}
