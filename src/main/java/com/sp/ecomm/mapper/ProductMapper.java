package com.sp.ecomm.mapper;

import com.sp.ecomm.dto.ProductDTO;
import com.sp.ecomm.entity.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public ProductDTO convertToProductDTO(Product brand) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(brand, ProductDTO.class);
    }
}
