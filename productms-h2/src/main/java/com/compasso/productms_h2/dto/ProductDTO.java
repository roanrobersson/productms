package com.compasso.productms_h2.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.compasso.productms_h2.entities.Product;

import io.swagger.annotations.ApiModelProperty;

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

	public ProductDTO() {
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
