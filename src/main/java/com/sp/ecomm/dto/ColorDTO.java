package com.sp.ecomm.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class ColorDTO {
	private Integer id;
	@NotNull(message = "Color name is required")
	private String name;
	private String code;
	private Set<ProductDTO> products;
}
