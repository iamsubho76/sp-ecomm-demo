package com.sp.ecomm.service;

import java.util.List;

import com.sp.ecomm.dto.BrandDTO;

public interface IBrandService {
	List<BrandDTO> findAll(int page, int size);
}
