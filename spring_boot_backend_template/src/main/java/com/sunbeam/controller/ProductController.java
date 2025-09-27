package com.sunbeam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dao.ProductDao;
import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.ProductDto;
import com.sunbeam.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductDao productDao;
//	@Autowired
//	private Product product;
	@PostMapping("/create")
	public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto){
		String name = productDto.getName();
		Boolean isPresent = productDao.existsByName(name);
		if(isPresent) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Product Already Exist"));
		}
		ProductDto productDtoSaved = productService.addProduct(productDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(productDtoSaved);
	}
	@GetMapping("/read/{name}")
	public ResponseEntity<?> readProduct(@PathVariable String name){
		ProductDto productDto = productService.getProduct(name);
		return ResponseEntity.ok(productDto);
	}
}
