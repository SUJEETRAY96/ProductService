package org.example.productservice.thirdPartyClient;

import org.example.productservice.dtos.FakeProductDto;
import org.example.productservice.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Objects.nonNull;

@Component
public class RestClient {
    @Value("${fake.store.url}")
    private String genericURL;

    private String allProductUrl = "https://fakestoreapi.com/products";

    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    public RestClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeProductDto getProduct(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeProductDto.class);
        ResponseExtractor<ResponseEntity<FakeProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeProductDto.class);
        ResponseEntity<FakeProductDto> responseEntity = restTemplate.execute(genericURL, HttpMethod.GET, requestCallback, responseExtractor, id);
        if(responseEntity.getBody()==null){
            throw new ProductNotFoundException("Product is not found with id :"+id);
        }
        return responseEntity.getBody();

    }

    public FakeProductDto[] getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeProductDto[]> responseEntity = restTemplate.getForEntity(allProductUrl, FakeProductDto[].class);
        return responseEntity.getBody();
    }

    public FakeProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
//        restTemplate.delete(specificProductUrl, id);
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeProductDto.class);
        ResponseExtractor<ResponseEntity<FakeProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeProductDto.class);

        ResponseEntity<FakeProductDto> responseEntity =
                restTemplate.execute(genericURL, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        return responseEntity.getBody();
    }


    public FakeProductDto addProduct(FakeProductDto fakeStoreProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeProductDto> responseEntity =
                restTemplate.postForEntity(allProductUrl,
                        fakeStoreProductDto, FakeProductDto.class);

        return responseEntity.getBody();
    }

    public void updateProductById() {

    }
}
