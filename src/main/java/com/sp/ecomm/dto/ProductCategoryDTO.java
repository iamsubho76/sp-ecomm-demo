package com.sp.ecomm.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProductCategoryDTO {
    private Integer id;
    private String name;
    private Set<ProductDTO> products;
}