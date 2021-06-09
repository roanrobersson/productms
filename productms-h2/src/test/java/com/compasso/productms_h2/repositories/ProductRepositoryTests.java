package com.compasso.productms_h2.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.compasso.productms_h2.entities.Product;

@DataJpaTest
public class ProductRepositoryTests {

	@Autowired
	ProductRepository repository;
	
	private int countTotalProducts;
	
	@BeforeEach
	void setUp() throws Exception {
		countTotalProducts = 15;
	}
	
	@Test
	public void search_WhenQueryExistsInProductName_ExpectedProducts() {
		String query = "Mouse";
		int expectedResultSize = 2;
		
		List<Product> result = repository.search(query, null, null);
	
		assertFalse(result.isEmpty());
		assertEquals(expectedResultSize, result.size());
	}
	
	@Test
	public void search_WhenQueryExistsInProductDescription_ExpectedProducts() {
		String query = "CoNsEcTeTuR";
		
		List<Product> result = repository.search(query, null, null);
	
		assertFalse(result.isEmpty());
		assertEquals(countTotalProducts, result.size());
	}
	
	@Test
	public void search_WhenQueryIsNull_ExpectedProducts() {
		List<Product> result = repository.search(null, null, null);
	
		assertFalse(result.isEmpty());
		assertEquals(countTotalProducts, result.size());
	}
	
	@Test
	public void search_WhenMinPriceMatchesWithProductPrice_ExpectedProcuts() {
		double minPrice = 100.0;
		int expectedResultSize = 11;
		
		List<Product> result = repository.search(null, minPrice, null);
		
		assertFalse(result.isEmpty());
		assertEquals(expectedResultSize, result.size());
	}
	
	@Test
	public void search_WhenMaxPriceMatchesWithProductPrice_ExpectedProcuts() {
		double maxPrice = 100L;
		int expectedResultSize = 4;
		
		List<Product> result = repository.search(null, null, maxPrice);
		
		assertFalse(result.isEmpty());
		assertEquals(expectedResultSize, result.size());
	}
}
