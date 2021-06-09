package com.compasso.productms_h2.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.compasso.productms_h2.dto.ProductDTO;
import com.compasso.productms_h2.services.ProductService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@ApiOperation(value = "Consulta um produto pelo ID")
	@ApiResponses({ 
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 400, message = "Produto não encontrado") })
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
		ProductDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@ApiOperation(value = "Retorna a lista de produtos")
	@ApiResponses({ @ApiResponse(code = 200, message = "Sucesso") })
	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
		List<ProductDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation(value = "Retorna a lista de produtos filtrados", notes = "")
	@ApiResponses({ @ApiResponse(code = 200, message = "Sucesso") })
	@GetMapping(value = "/search")
	public ResponseEntity<List<ProductDTO>> search(
			@ApiParam(value = "Frase utilizada na busca pelo nome e descrição dos produtos") @RequestParam(value = "q", required = false) String q,
			@ApiParam(value = "Valor mínimo") @RequestParam(value = "min_price", required = false) Double minPrice,
			@ApiParam(value = "Valor máximo") @RequestParam(value = "max_price", required = false) Double maxPrice) {
		List<ProductDTO> list = service.search(q, minPrice, maxPrice);
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation(value = "Adiciona um novo produto")
	@ApiImplicitParam(name = "dto", value = "Objeto a ser adicionado", dataType = "com.compasso.productms_h2.dto.ProductDTO")
	@ApiResponses({ @ApiResponse(code = 201, message = "Produto adicionado com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação") })
	@PostMapping
	public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@ApiOperation(value = "Atualiza um produto")
	@ApiImplicitParam(name = "dto", value = "Objeto para atualização", dataType = "com.compasso.productms_h2.dto.ProductDTO")
	@ApiResponses({ @ApiResponse(code = 201, message = "Produto adicionado com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação"),
			@ApiResponse(code = 404, message = "Produto não encontrado") })
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@ApiOperation(value = "Deleta um produto")
	@ApiResponses({ @ApiResponse(code = 201, message = "Produto deletado com sucesso"),
			@ApiResponse(code = 404, message = "Produto não encontrado") })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
