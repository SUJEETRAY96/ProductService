package org.example.productservice.services;

import org.example.productservice.dtos.FakeProductDto;
import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.thirdPartyClient.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service("FakeProductService")
public class FakeProductServiceImpl implements ProductService{
    private RestClient restClient;
    @Autowired
    public FakeProductServiceImpl(RestClient restClient){
        this.restClient = restClient;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return getProductFromFakeStoreProductDto(restClient.getProduct(id));
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new LinkedList<>();
        for(FakeProductDto fakeProductDto: restClient.getAllProducts()) {
            productList.add(getProductFromFakeStoreProductDto(fakeProductDto));
        }
        return productList;
    }

    @Override
    public Product deleteProductById(Long id) {
        return getProductFromFakeStoreProductDto(restClient.deleteProductById(id));
    }

    @Override
    public Product addProduct(Product product) {
        return getProductFromFakeStoreProductDto(restClient.addProduct(getFakeStoreProductDtoFromProduct(product)));
    }

    @Override
    public void updateProductById() {

    }

    private Product getProductFromFakeStoreProductDto(FakeProductDto fakeProductDto){
        Product product = new Product();
        product.setId(fakeProductDto.getId());
        product.setTitle(fakeProductDto.getTitle());
        product.setDescription(fakeProductDto.getDescription());
        Category category = new Category();
        category.setName(fakeProductDto.getCategory());
        product.setCategory(category);
        product.setPrice(fakeProductDto.getPrice());

        return product;
    }

    private FakeProductDto getFakeStoreProductDtoFromProduct(Product product){
        FakeProductDto fakeProductDto = new FakeProductDto();
        fakeProductDto.setTitle(product.getTitle());
        fakeProductDto.setDescription(product.getDescription());
        fakeProductDto.setCategory(product.getCategory().getName());
        fakeProductDto.setPrice(product.getPrice());
        return fakeProductDto;
    }
}
