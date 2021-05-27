package com.compasso.productms_h2.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compasso.productms_h2.dto.ProductDTO;
import com.compasso.productms_h2.entities.Product;
import com.compasso.productms_h2.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired 
	private ModelMapper modelMapper;

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		return new ProductDTO(repository.findById(id).orElseThrow());
	}

	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		return repository.findAll().stream()
				.map(p -> new ProductDTO(p))
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<ProductDTO> search(String q, Double minPrice, Double maxPrice) {
		return repository.search(q, minPrice, maxPrice).stream()
				.map(p -> new ProductDTO(p))
				.collect(Collectors.toList());
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = repository.save(modelMapper.map(dto, Product.class));
		return modelMapper.map(entity, ProductDTO.class);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		Product entity = repository.save(repository.getOne(id));
		return modelMapper.map(entity, ProductDTO.class);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}