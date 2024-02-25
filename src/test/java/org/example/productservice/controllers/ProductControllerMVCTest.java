package org.example.productservice.controllers;

import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Product;
import org.example.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.client.match.JsonPathRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import javax.swing.text.AbstractDocument;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMVCTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean(name = "SelfProductService")
    private ProductService productService;

    @Test
    public void testGetProductById() throws Exception {
        Product dummyProduct = new Product();
        dummyProduct.setId(1L);
        dummyProduct.setTitle("Dummy Product");
        when(productService.getProductById(1L)).thenReturn(dummyProduct);

        mockMvc.perform(get("/products/{id}", 1L))
                .andExpect(status().isOk())
                //.andExpect((ResultMatcher) jsonPath("$.id").value(1L))
              //  .andExpect((ResultMatcher) jsonPath("$.title").value("Dummy Product"))
                .andExpect(content().contentType("application/json"));
    }
}
