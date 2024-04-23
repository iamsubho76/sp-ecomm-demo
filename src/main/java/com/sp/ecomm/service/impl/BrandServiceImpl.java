package com.sp.ecomm.service.impl;

import java.util.List;
import java.util.function.Function;

import com.sp.ecomm.dto.BrandDTO;
import com.sp.ecomm.exception.B2CResourceNotFoundException;
import com.sp.ecomm.mapper.BrandMapper;
import com.sp.ecomm.service.IBrandService;
import com.sp.ecomm.utils.B2cEcommerceApplicationConstants;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sp.ecomm.entity.Brand;
import com.sp.ecomm.repository.BrandRepository;

@Service
public class BrandServiceImpl implements IBrandService {
	
	@Autowired
	BrandRepository brandRepository;

	@Autowired
	BrandMapper mapper;

	@Override
	public List<BrandDTO> findAll(int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		Page<Brand> pageBrandEntity = brandRepository.findAll(paging);

		if(!pageBrandEntity.getContent().isEmpty() || pageBrandEntity.getContent() != null) {
			Page<BrandDTO> pageBrandDTO = pageBrandEntity.map(new Function<Brand, BrandDTO>() {
				@Override
				public BrandDTO apply(Brand entity) {
					BrandDTO dto = new BrandDTO();
					// Conversion logic
					dto = mapper.convertToBrandDTO(entity);
					return dto;
				}
			});
			return pageBrandDTO.getContent();
		} else {
			throw new B2CResourceNotFoundException(1001, "Brand", "", "all", MDC.get(B2cEcommerceApplicationConstants.TRANSACTION_ID));
		}
	}
}
