package org.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.productservice.models.Category;
@Getter
@Setter
public class FakeProductDTO {
    private Long id;
    private String title;
    private String description;
    private Long price;
    private String category;
}
