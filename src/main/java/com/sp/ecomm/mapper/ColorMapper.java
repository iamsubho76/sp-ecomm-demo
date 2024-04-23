package com.sp.ecomm.mapper;

import com.sp.ecomm.dto.ColorDTO;
import com.sp.ecomm.entity.Color;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
public class ColorMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public ColorDTO convertToColorDTO(Color color) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(color, ColorDTO.class);
    }
}
