package org.example.productservice.security.services;

import org.example.productservice.security.dtos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationService {


    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    public AuthenticationService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public boolean authenticateUser(String token){
        ResponseEntity<User> userResponseEntity = restTemplateBuilder.build().postForEntity("http://localhost:9000/users/validate/"+token,
                null, User.class);
        if(userResponseEntity.getBody()!=null){
            return true;
        }
        return false;
    }
}
