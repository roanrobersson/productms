package com.compasso.productms_rabbitmq.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compasso.productms_rabbitmq.dto.ProductDTO;
import com.compasso.productms_rabbitmq.entities.Product;
import com.compasso.productms_rabbitmq.repository.ProductRepository;
import com.compasso.productms_rabbitmq.services.exceptions.ResourceNotFoundException;
import com.compasso.productms_rabbitmq.services.messagery.MessageProducerService;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	public MessageProducerService sender;

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		sender.send("products_queue", "Produto id:'" + entity.getId() + "' consultado");
		return new ProductDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		List<Product> list = repository.findAll();
		sender.send("products_queue", "Lista completa de produtos consultada");
		return list.stream().map(p -> new ProductDTO(p)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<ProductDTO> search(String q, Double minPrice, Double maxPrice) {
		List<Product> list = repository.search(q, minPrice, maxPrice);
		sender.send("products_queue", "Pesquisa '" + q + "' realizada");
		return list.stream().map(p -> new ProductDTO(p)).collect(Collectors.toList());
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		this.copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		sender.send("products_queue", "Produto id:'" + entity.getId() + "' inserido");
		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getOne(id);
			this.copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			sender.send("products_queue", "Produto id:'" + entity.getId() + "' atualizado");
			return new ProductDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id " + id + " not found");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
			sender.send("products_queue", "Produto id:'" + id + "' deletado");
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id " + id + " not found");
		}
	}

	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
	}
}