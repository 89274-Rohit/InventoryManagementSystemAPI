package com.sunbeam.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.dao.ProductDao;
import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.ProductDto;
import com.sunbeam.entities.Product;
import com.sunbeam.exception.InvalidStockOperationException;
import com.sunbeam.exception.ProductNotFoundException;
import com.sunbeam.services.ProductService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProductDto addProduct(ProductDto productDto) {
		Product product = modelMapper.map(productDto, Product.class);
		Product productSaved = productDao.save(product);
		ProductDto productDtoRet = modelMapper.map(productSaved, ProductDto.class);
		return productDtoRet;
	}

	@Override
	public ProductDto getProduct(String name) {
		Optional<Product> product = productDao.findByNameAndIsActiveTrue(name);
		if(product.isEmpty()) {
			throw new ProductNotFoundException("Product not found with name "+name);
		}
		ProductDto productDto = modelMapper.map(product, ProductDto.class);
		return productDto;
	}

	@Override
	public ApiResponse removeProductByName(String name) {
		Product product = productDao.findByName(name);
		product.setActive(false);
		return new ApiResponse("Product SucessFully Deleted");
	}

	@Override
	public List<ProductDto> getAllActiveProducts() {
		List<Product> product = productDao.findByisActiveTrue();
		ArrayList<ProductDto> list = new ArrayList<>();
		for(Product p : product) {
			list.add(modelMapper.map(p, ProductDto.class));
		}
		return list;
	}

	@Override
	public ProductDto updateProductAdd(String name, Long quantity) {
		Optional<Product> product = productDao.findByNameAndIsActiveTrue(name);
		if(product.isEmpty()) {
			throw new ProductNotFoundException("Product not found with name "+name);
		}
		if(quantity<=0) {
			throw new InvalidStockOperationException("Please Enter value greater than zero");
		}
		Long quan = product.get().getStock_quantity();
		product.get().setStock_quantity(quan + quantity);
		ProductDto productDto = modelMapper.map(product, ProductDto.class);
		return productDto;
	}

	@Override
	public ProductDto updateProductRemove(String name, Long quantity) {
		Optional<Product> product = productDao.findByNameAndIsActiveTrue(name);
		if(product.isEmpty()) {
			throw new ProductNotFoundException("Product not found with name "+name);
		}
		if(quantity<=0) {
			throw new InvalidStockOperationException("Please Enter value greater than zero");
		}
		if(quantity>product.get().getStock_quantity()) {
			throw new InvalidStockOperationException("Entered quantity is not available in stock please try with lesser quantity");
		}
		Long quan = product.get().getStock_quantity();
		product.get().setStock_quantity(quan - quantity);
		ProductDto productDto = modelMapper.map(product,ProductDto.class);
		return productDto;		
	}
}
