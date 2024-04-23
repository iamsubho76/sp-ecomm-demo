package com.sp.ecomm.service.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.sp.ecomm.dto.ProductDTO;
import com.sp.ecomm.exception.B2CException;
import com.sp.ecomm.exception.B2CResourceNotFoundException;
import com.sp.ecomm.mapper.ProductMapper;
import com.sp.ecomm.service.IProductService;
import com.sp.ecomm.utils.B2cEcommerceApplicationConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sp.ecomm.entity.Product;
import com.sp.ecomm.repository.ProductRepository;

enum SearchBy {

	BRAND("brand"), COLOR("color"), SIZE("size");
	String value;

	SearchBy(String name) {
		value = name;
	}

	String getValue() {
		return value;
	}
}

@Service
@Slf4j
public class ProductServiceImpl implements IProductService {
	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductMapper mapper;

	@Override
	public List<ProductDTO> findAll(int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		Page<Product> pageProductEntity = productRepository.findAll(paging);

		if(!pageProductEntity.getContent().isEmpty() || pageProductEntity.getContent() != null) {
			Page<ProductDTO> pageBrandDTO = pageProductEntity.map(new Function<Product, ProductDTO>() {
				@Override
				public ProductDTO apply(Product entity) {
					// Conversion logic
					return mapper.convertToProductDTO(entity);
				}
			});
			return pageBrandDTO.getContent();
		} else {
			throw new B2CResourceNotFoundException(1001, "Product", "", "all", MDC.get(B2cEcommerceApplicationConstants.TRANSACTION_ID));
		}
	}

	@Override
	public List<ProductDTO> getProducts(String searchByOption, String actualValue) {
		List<Product> listOfProducts = null;
		SearchBy searchBy = SearchBy.valueOf(searchByOption.toUpperCase());
		switch (searchBy) {
			case BRAND:
				listOfProducts = productRepository.findByBrandId(Integer.valueOf(actualValue));
				break;
			case COLOR:
				listOfProducts = productRepository.findByColorId(Integer.valueOf(actualValue));
				break;
			case SIZE:
				listOfProducts = productRepository.findBySize(actualValue);
				break;
			default:
				log.error("Not a valid search option.");
				throw new B2CException(1000, "provide either brand or color or size to search", MDC.get(B2cEcommerceApplicationConstants.TRANSACTION_ID));
		}

		if(ObjectUtils.isEmpty(listOfProducts)) {
			throw new B2CResourceNotFoundException(1001, "Product", searchByOption, actualValue, MDC.get(B2cEcommerceApplicationConstants.TRANSACTION_ID));
		} else {
			return listOfProducts.stream()
					.map(mapper::convertToProductDTO)
					.collect(Collectors.toList());
		}
	}
}
