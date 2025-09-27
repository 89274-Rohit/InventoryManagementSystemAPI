package com.sunbeam.services;

import com.sunbeam.dto.ProductDto;

public interface ProductService {
	//public ProductDto addProduct(Long id,String name,String description,Long stock_quantity);
	public ProductDto addProduct(ProductDto productDto);
	public ProductDto getProduct(String name);

}
