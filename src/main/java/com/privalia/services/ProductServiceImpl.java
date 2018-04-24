package com.privalia.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privalia.domain.Product;
import com.privalia.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private final Logger logger= LoggerFactory.getLogger(ProductServiceImpl.class);
	private ProductRepository productRepository;
	
	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}


	public Iterable<Product> listAllProducts() {
		logger.debug("listAllProducts");
		return productRepository.findAll();
	}

	
	public Product saveProduct(Product product) {
		logger.debug("saveProduct");
		return productRepository.save(product);
	}


	public Product getProductById(Integer id) {
		logger.debug("getProductById");
		return productRepository.findOne(id);
	}

	
	public void deleteProduct(Integer id) {
		logger.debug("deleteProduct");
		productRepository.delete(id);
	}


}

