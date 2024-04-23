package com.sp.ecomm.mapper;

import com.sp.ecomm.dto.ProductCategoryDTO;
import com.sp.ecomm.entity.ProductCategory;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public ProductCategoryDTO convertToProductCategoryDTO(ProductCategory productCategory) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(productCategory, ProductCategoryDTO.class);
    }
}
