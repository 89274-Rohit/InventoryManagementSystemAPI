package com.sunbeam.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.dao.ProductDao;
import com.sunbeam.dto.ProductDto;
import com.sunbeam.entities.Product;
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
		Product product = productDao.findByName(name);
		ProductDto productDto = modelMapper.map(product, ProductDto.class);
		return productDto;
	}

}
