package com.sp.ecomm.controller.impl;

import java.util.List;

import com.sp.ecomm.controller.IColorController;
import com.sp.ecomm.dto.ColorDTO;
import com.sp.ecomm.dto.Response;
import com.sp.ecomm.dto.ResponseStatus;
import com.sp.ecomm.utils.B2cEcommerceApplicationUtil;
import com.sp.ecomm.service.IColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/colors")
public class ColorControllerImpl implements IColorController {
	@Autowired
    IColorService colorService;

	@Override
	public ResponseEntity<Response<List<ColorDTO>>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
		Response<List<ColorDTO>> colorDTOResponse = new Response<>();
		List<ColorDTO> colors = colorService.findAll(page, size);
		colorDTOResponse.setMessage("color data found");
		colorDTOResponse.setData(colors);
		B2cEcommerceApplicationUtil.buildResponse(colorDTOResponse, ResponseStatus.SUCCESS);
		return new ResponseEntity<>(colorDTOResponse, HttpStatus.OK);
	}
}