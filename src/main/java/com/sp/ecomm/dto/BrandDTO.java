package com.sp.ecomm.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class BrandDTO {
	private Integer id;
	@NotNull(message = "Brand name cannot be empty.")
	private String name;
	private Set<ProductDTO> products;
}
