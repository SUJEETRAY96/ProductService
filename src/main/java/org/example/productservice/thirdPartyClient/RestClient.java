package org.example.productservice.thirdPartyClient;

import org.example.productservice.dtos.FakeProductDTO;
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

import static java.util.Objects.nonNull;

@Component
public class RestClient {
    @Value("${fake.store.url}")
    private String genericURL;

    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    public RestClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeProductDTO getProduct(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeProductDTO.class);
        ResponseEntity<FakeProductDTO> responseEntity = restTemplate.execute(genericURL, HttpMethod.GET, requestCallback, responseExtractor, id);
        if(responseEntity.getBody()==null){
            throw new ProductNotFoundException("Product is not found with id :"+id);
        }
        return responseEntity.getBody();

    }
}
