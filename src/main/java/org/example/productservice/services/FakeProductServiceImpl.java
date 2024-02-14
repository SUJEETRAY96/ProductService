package org.example.productservice.services;

import org.example.productservice.dtos.FakeProductDTO;
import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.thirdPartyClient.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("FakeProductService")
public class FakeProductServiceImpl implements ProductService{
    private RestClient restClient;
    @Autowired
    public FakeProductServiceImpl(RestClient restClient){
        this.restClient = restClient;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return getProductFromFakeProductDTO(restClient.getProduct(id));
    }
    private Product getProductFromFakeProductDTO(FakeProductDTO fakeProductDTO){
        Product product = new Product();
        product.setId(fakeProductDTO.getId());
        product.setPrice(fakeProductDTO.getPrice());
        Category category = new Category();
        category.setCategory(fakeProductDTO.getCategory());
        product.setCategory(category);
        product.setTitle(fakeProductDTO.getTitle());
        product.setDescription(fakeProductDTO.getDescription());
        return product;
    }
}
