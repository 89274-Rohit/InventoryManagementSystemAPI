package com.sunbeam.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunbeam.entities.Product;

public interface ProductDao extends JpaRepository<Product, Long>{
	Product findByName(String name);
	Boolean existsByName(String name);
	void deleteByName(String name);
	List<Product> findByisActiveTrue();
	//@Query("select p from Product p where p.isActive=true and name=:pname")
	Optional<Product> findByNameAndIsActiveTrue(String name);
	
}
