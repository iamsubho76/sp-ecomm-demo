package com.sp.ecomm.controller.impl;

import java.util.List;

import com.sp.ecomm.controller.IProductCategoryController;
import com.sp.ecomm.dto.ProductCategoryDTO;
import com.sp.ecomm.dto.Response;
import com.sp.ecomm.dto.ResponseStatus;
import com.sp.ecomm.utils.B2cEcommerceApplicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sp.ecomm.service.IProductCategoryService;

@RestController
@RequestMapping("/api/v1/productcategories")
public class ProductCategoryControllerImpl implements IProductCategoryController {
	@Autowired
    IProductCategoryService productCategoryService;

	@Override
	public ResponseEntity<Response<List<ProductCategoryDTO>>> findAll(int page, int size) {
		Response<List<ProductCategoryDTO>> productCategoryDTOResponse = new Response<>();
		List<ProductCategoryDTO> productCategories = productCategoryService.findAll(page, size);
		productCategoryDTOResponse.setMessage("ProductCategory data found");
		productCategoryDTOResponse.setData(productCategories);
		B2cEcommerceApplicationUtil.buildResponse(productCategoryDTOResponse, ResponseStatus.SUCCESS);
		return new ResponseEntity<>(productCategoryDTOResponse, HttpStatus.OK);
	}
}