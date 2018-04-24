package com.privalia.services;

import com.privalia.domain.Product;

public interface ProductService {

	 Iterable<Product> listAllProducts();
	 
	 Product saveProduct(Product product);
	 
	 Product getProductById(Integer id);
	 
	 void deleteProduct(Integer id);
}
