package com.robsonArcoleze.dscommerce.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.robsonArcoleze.dscommerce.DTO.ProductDTO;
import com.robsonArcoleze.dscommerce.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/products")
@Tag(name = "Products", description = "Endpoint for Managing Products")
public class ProductController {

	@Autowired
	private ProductService service;
	

	@GetMapping(value = "/{id}")
	@Operation(summary = "Finds a product", description = "Finds a product", tags = {"Products"}, 
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = @Content(schema = @Schema(implementation = ProductDTO.class))	),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
		ProductDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	@Operation(summary = "Finds all products", description = "Finds all products", tags = {"Products"}, 
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = @Content(schema = @Schema(implementation = ProductDTO.class))	),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
		Page<ProductDTO> dto = service.findAll(pageable);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	@Operation(summary = "Add a new product", description = "Add a new product", tags = {"Products"}, 
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = @Content(schema = @Schema(implementation = ProductDTO.class))	),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public  ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	@Operation(summary = "Update a product", description = "Update a product", tags = {"Products"}, 
	responses = {
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Delete a product", description = "Delete a product", tags = {"Products"}, 
	responses = {
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
