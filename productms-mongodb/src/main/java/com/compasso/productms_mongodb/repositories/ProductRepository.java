package com.compasso.productms_mongodb.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.compasso.productms_mongodb.entities.Product;

@Repository

public interface ProductRepository extends MongoRepository<Product, String>{
	
	@Query("{ $and: ["
			+ "  { $or: ["
			+ "      { 'name': { $regex: /?0/, $options: 'i' } }," 
			+ "      { 'description': { $regex: /?0/, $options: 'i' } }"
			+ "    ] },"
			+ "  { 'price': {$gte: ?1} },"
			+ "  { 'price': {$lte: ?2} }"
			+ "] }")
	public List<Product> search(String q, Double minPrice, Double maxPrice);
}
