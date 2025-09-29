package com.sunbeam.services;

import java.util.List;

import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.ProductDto;

public interface ProductService {
	ProductDto addProduct(ProductDto productDto);
	ProductDto getProduct(String name);
	ApiResponse removeProductByName(String name);
	List<ProductDto> getAllActiveProducts();
	ProductDto updateProductAdd(String name,Long quantity);
	ProductDto updateProductRemove(String name,Long quantity);
	List<ProductDto> getAllProductBelowThreshold();
}
