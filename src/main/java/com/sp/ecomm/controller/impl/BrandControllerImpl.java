package com.sp.ecomm.controller.impl;

import java.util.List;

import com.sp.ecomm.controller.IBrandController;
import com.sp.ecomm.dto.BrandDTO;
import com.sp.ecomm.dto.Response;
import com.sp.ecomm.dto.ResponseStatus;
import com.sp.ecomm.utils.B2cEcommerceApplicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sp.ecomm.service.IBrandService;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandControllerImpl implements IBrandController {
	@Autowired
	IBrandService brandService;

		@Override
		public ResponseEntity<Response<List<BrandDTO>>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
			Response<List<BrandDTO>> brandDTOResponse = new Response<>();
			List<BrandDTO> brands = brandService.findAll(page, size);
			brandDTOResponse.setMessage("Brand data found");
			brandDTOResponse.setData(brands);
			B2cEcommerceApplicationUtil.buildResponse(brandDTOResponse, ResponseStatus.SUCCESS);
			return new ResponseEntity<>(brandDTOResponse, HttpStatus.OK);
		}
}