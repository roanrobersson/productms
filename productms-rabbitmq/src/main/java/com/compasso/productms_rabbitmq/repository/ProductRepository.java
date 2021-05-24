package com.compasso.productms_rabbitmq.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.compasso.productms_rabbitmq.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query("SELECT p FROM Product p WHERE "
			+ "(:q IS NULL OR "
			+ "LOWER(p.name) LIKE CONCAT('%', LOWER(:q), '%') OR "
			+ "LOWER(p.description) LIKE CONCAT('%', LOWER(:q), '%')) AND "			
			+ "(:minPrice IS NULL OR p.price >= :minPrice) AND "
			+ "(:maxPrice IS NULL OR p.price <= :maxPrice)")
	List<Product> search(String q, Double minPrice, Double maxPrice);
}