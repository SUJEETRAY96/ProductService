package org.example.productservice.thirdPartyClient;

import org.example.productservice.dtos.FakeProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {
    @Value("${fake.store.url}")
    private String genericURL;

    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    public RestClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeProductDTO getProduct(Long id){
        RestTemplate restTemplate = restTemplateBuilder.build();
        return restTemplate.getForEntity(genericURL, FakeProductDTO.class,id).getBody();
      //  return restTemplate.getForEntity(genericURL, Product.class,id).getBody();
    }
}
