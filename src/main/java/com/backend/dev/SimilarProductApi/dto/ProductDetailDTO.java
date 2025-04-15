package com.backend.dev.SimilarProductApi.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDTO {
    private String id;
    private String name;
    private Double price;
    private Boolean availability;
}