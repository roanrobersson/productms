package com.compasso.productms_h2.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.compasso.productms_h2.entities.Product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ProductDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	Long id;

	@NotBlank
	String name;

	@ApiModelProperty(notes = "Descrição do produto")
	@NotBlank
	String description;

	@NotNull
	@Positive
	Double price;
		
	public ProductDTO(Long id, String name, String description, Double price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public ProductDTO(Product entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
	}
	
}
