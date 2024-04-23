package com.sp.ecomm.service;

import java.util.List;

import com.sp.ecomm.dto.ProductDTO;

public interface IProductService {
	List<ProductDTO> findAll(int page, int size);
	List<ProductDTO> getProducts(String searchByOption, String actualValue);
}
