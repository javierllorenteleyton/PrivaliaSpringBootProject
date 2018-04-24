package com.privalia.restcontrollers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.privalia.domain.Product;
import com.privalia.services.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/product")
@Api(value="onlinestore", tags ="Operations pertaining to products in online Store")
public class ProductRestcontroller {
  
	private ProductService productService;

	@Autowired 
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@ApiOperation(value = "Search", response = Product.class)
	@RequestMapping(value="/show/{id}",method = RequestMethod.GET,produces = "application/json")
	public Product showProduct(@PathVariable Integer id) {
		Product product= productService.getProductById(id);
		return product;
	}
	
	@ApiOperation(value = "View List", response = Iterable.class)
    @ApiResponses( value = { 
        @ApiResponse(code= 200, message="Sucess"),
        @ApiResponse(code= 400, message="Not authorized"),
        @ApiResponse(code= 401, message="Accesin"),
        @ApiResponse(code= 402, message="The resource")
    })
	@RequestMapping(value="/list",method = RequestMethod.GET,produces = "application/json")
	public Iterable<Product> list() {
		Iterable<Product> products= productService.listAllProducts();
		return products;
	}
	
	@ApiOperation(value = "Add product")
	@RequestMapping(value="/add",method = RequestMethod.POST,produces = "application/json")
	public ResponseEntity<String> saveProduct(@Valid @RequestBody Product product) {
		productService.saveProduct(product);
		return new ResponseEntity<String>("Prodcut",HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update product")
	@RequestMapping(value="/update/{id}",method = RequestMethod.PUT,produces = "application/json")
	public ResponseEntity<String> updateProduct(@PathVariable Integer id,@Valid @RequestBody Product product) {
	    Product  storeProduct= productService.getProductById(id);
	    storeProduct.setDescription(product.getDescription());
	    storeProduct.setPrice(product.getPrice());
	    storeProduct.setImageUrl(product.getImageUrl());
		return new ResponseEntity<String>("Prodcut update",HttpStatus.OK);
	}
	
	@ApiOperation(value = "delete product")
	@RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE,produces = "application/json")
	public ResponseEntity<String> delete(@PathVariable( value="id",required = true) Integer id) {
	    productService.deleteProduct(id);
		return new ResponseEntity<String>("Prodcut delete",HttpStatus.OK);
	}
}
