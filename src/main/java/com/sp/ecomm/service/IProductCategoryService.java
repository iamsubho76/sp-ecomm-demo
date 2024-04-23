package com.sp.ecomm.service;

import java.util.List;

import com.sp.ecomm.dto.ProductCategoryDTO;

public interface IProductCategoryService {
	List<ProductCategoryDTO> findAll(int page, int size);
}
