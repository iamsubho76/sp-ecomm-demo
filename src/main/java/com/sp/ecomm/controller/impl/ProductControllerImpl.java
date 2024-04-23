package com.sp.ecomm.controller.impl;

import java.util.List;

import com.sp.ecomm.controller.IProductController;
import com.sp.ecomm.dto.ProductDTO;
import com.sp.ecomm.dto.Response;
import com.sp.ecomm.dto.ResponseStatus;
import com.sp.ecomm.utils.B2cEcommerceApplicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sp.ecomm.service.IProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductControllerImpl implements IProductController {
    @Autowired
    IProductService productService;

    @GetMapping
    public ResponseEntity<Response<List<ProductDTO>>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        Response<List<ProductDTO>> productDTOResponse = new Response<>();
        List<ProductDTO> products = productService.findAll(page, size);
        productDTOResponse.setMessage("product data found");
        productDTOResponse.setData(products);
        B2cEcommerceApplicationUtil.buildResponse(productDTOResponse, ResponseStatus.SUCCESS);
        return new ResponseEntity<>(productDTOResponse, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Response<List<ProductDTO>>> search(@RequestParam("name") String name, @RequestParam("value") String value) {
        Response<List<ProductDTO>> productDTOResponse = new Response<>();
        List<ProductDTO> products = productService.getProducts(name, value);
        productDTOResponse.setMessage(name.concat(" product data found for ").concat(value));
        productDTOResponse.setData(products);
        B2cEcommerceApplicationUtil.buildResponse(productDTOResponse, ResponseStatus.SUCCESS);
        return new ResponseEntity<>(productDTOResponse, HttpStatus.OK);
    }
}