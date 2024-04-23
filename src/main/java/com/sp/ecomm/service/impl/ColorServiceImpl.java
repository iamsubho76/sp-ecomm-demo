package com.sp.ecomm.service.impl;

import java.util.List;
import java.util.function.Function;

import com.sp.ecomm.dto.ColorDTO;
import com.sp.ecomm.exception.B2CResourceNotFoundException;
import com.sp.ecomm.mapper.ColorMapper;
import com.sp.ecomm.service.IColorService;
import com.sp.ecomm.utils.B2cEcommerceApplicationConstants;
import com.sp.ecomm.entity.Color;
import com.sp.ecomm.repository.ColorRepository;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ColorServiceImpl implements IColorService {

    @Autowired
    ColorRepository colorRepository;


    @Autowired
    ColorMapper mapper;

    @Override
    public List<ColorDTO> findAll(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Color> pageColorEntity = colorRepository.findAll(paging);

        if (!pageColorEntity.getContent().isEmpty() || pageColorEntity.getContent() != null) {
            Page<ColorDTO> pageColorDTO = pageColorEntity.map(new Function<Color, ColorDTO>() {
                @Override
                public ColorDTO apply(Color entity) {
                    // Conversion logic
                    return mapper.convertToColorDTO(entity);
                }
            });
            return pageColorDTO.getContent();
        } else {
            throw new B2CResourceNotFoundException(1001, "COLOR", "", "all", MDC.get(B2cEcommerceApplicationConstants.TRANSACTION_ID));
        }
    }
}
