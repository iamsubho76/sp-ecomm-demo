package com.sp.ecomm.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductDTO {
	private Integer id;
	@NotNull(message = "Product name cannot be empty.")
	private String name;
	@NotNull(message = "Product quantity cannot be empty.")
	private Integer quantity;
	@NotNull(message = "Product size is required")
	private String size;
	private Integer brandId;
	private Integer colorId;
	private Integer catagoryId;
	private BrandDTO brand;
	private ColorDTO color;
	private ProductCategoryDTO productCategory;
}
