package com.sp.ecomm.service.impl;

import java.util.List;
import java.util.function.Function;

import com.sp.ecomm.dto.ProductCategoryDTO;
import com.sp.ecomm.exception.B2CResourceNotFoundException;
import com.sp.ecomm.mapper.ProductCategoryMapper;
import com.sp.ecomm.service.IProductCategoryService;
import com.sp.ecomm.utils.B2cEcommerceApplicationConstants;
import com.sp.ecomm.entity.ProductCategory;
import com.sp.ecomm.repository.ProductCategoryRepository;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {
	
	@Autowired
    ProductCategoryRepository productCategoryRepository;

	@Autowired
    ProductCategoryMapper mapper;

	@Override
	public List<ProductCategoryDTO> findAll(int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		Page<ProductCategory> pageBrandEntity = productCategoryRepository.findAll(paging);

		if(!pageBrandEntity.getContent().isEmpty() || pageBrandEntity.getContent() != null) {
			Page<ProductCategoryDTO> pageProductCategoryDTO = pageBrandEntity.map(new Function<ProductCategory, ProductCategoryDTO>() {
				@Override
				public ProductCategoryDTO apply(ProductCategory entity) {
					// Conversion logic
					ProductCategoryDTO dto = mapper.convertToProductCategoryDTO(entity);
					return dto;
				}
			});
			return pageProductCategoryDTO.getContent();
		} else {
			throw new B2CResourceNotFoundException(1101, "ProductCategory", "", "all", MDC.get(B2cEcommerceApplicationConstants.TRANSACTION_ID));
		}
	}

}
