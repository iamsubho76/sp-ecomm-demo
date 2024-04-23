package com.sp.ecomm.service;

import java.util.List;

import com.sp.ecomm.dto.ColorDTO;

public interface IColorService {
	List<ColorDTO> findAll(int page, int size);
}
