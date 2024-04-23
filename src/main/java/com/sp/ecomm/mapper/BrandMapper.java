package com.sp.ecomm.mapper;

import com.sp.ecomm.dto.BrandDTO;
import com.sp.ecomm.entity.Brand;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
public class BrandMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public BrandDTO convertToBrandDTO(Brand brand) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(brand, BrandDTO.class);
    }
}
