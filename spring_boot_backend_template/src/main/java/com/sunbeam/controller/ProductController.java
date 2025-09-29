package com.sunbeam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	@GetMapping("/read")
	public ResponseEntity<?> readAllProduct(){
		List<ProductDto> productDto = productService.getAllActiveProducts();
		return ResponseEntity.ok(productDto);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteProduct(@RequestParam String name){
		return ResponseEntity.ok(productService.removeProductByName(name));
	}
	@PutMapping("/addupdate")
	public ResponseEntity<?> updateProductAdd(@RequestParam String name,@RequestParam Long quantity){
		ProductDto productDto = productService.updateProductAdd(name, quantity);
		return ResponseEntity.ok(productDto);
	}
	@PutMapping("/removeupdate")
	public ResponseEntity<?> updateProductRemove(@RequestParam String name,@RequestParam Long quantity){
		ProductDto productDto = productService.updateProductRemove(name, quantity);
		return ResponseEntity.ok(productDto);
	}
	@GetMapping("/readlowstock")
	public ResponseEntity<?> reaProductLowStockThreshold(){
		List<ProductDto> productDto = productService.getAllProductBelowThreshold();
		return ResponseEntity.ok(productDto);
	}
}
