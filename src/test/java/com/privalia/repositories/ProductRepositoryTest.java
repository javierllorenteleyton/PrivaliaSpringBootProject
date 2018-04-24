package com.privalia.repositories;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.privalia.configuration.RepositoryConfiguration;
import com.privalia.domain.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={RepositoryConfiguration.class})
public class ProductRepositoryTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryTest.class);
	
	private ProductRepository productRepository;
	private Product product1 = null;
	private Product product2 = null;
	
	@Rule
	public TestName testName= new TestName();
	
	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository= productRepository;;
	}

	@Before
	public void setUp() {
		product1= new Product();
		product1.setDescription("Privalia Spring framework");
		product1.setPrice(new BigDecimal("23.85"));
		product1.setProductId("1232");
		productRepository.save(product1);		
		
		product2= new Product();
		product2.setDescription("Privalia Hibernate framework");
		product2.setPrice(new BigDecimal("23.85"));
		product2.setProductId("1233");
		productRepository.save(product2);	
		LOGGER.info("Started test" + testName.getMethodName());
	}
	
	@After
	public void aftar() throws Exception {
		LOGGER.info("Finished test" + testName.getMethodName());
		productRepository.deleteAll();
	}
	
	@Test
	public void testSaveProduct() {
		Product product= new Product();
		product.setDescription("Spring framework Guru Shirt");
		product.setPrice(new BigDecimal("18.95"));
		product.setProductId("1234");
		assertNull(product.getId());
		productRepository.save(product);
		assertNotNull(product.getId());
	}
	
	@Test
	public void testGetAllProducts() {
		Iterable<Product> products = productRepository.findAll();
		long size = products.spliterator().getExactSizeIfKnown();
		assertEquals(size, 2);
		
	}
	
	@Test
	public void testModifyProduct() {
		product1.setDescription("New Description");
		Product updatedProduct= productRepository.save(product1);
		assertEquals(updatedProduct.getDescription(), "New Description");
	}
	
	@Test
	public void testDeleteProductById() {
		productRepository.delete(product1.getId());
		assertNull(productRepository.findOne(product1.getId()));
	}
	
	@Test
	public void testfindByProductId() {
		assertNotNull(productRepository.findByProductId(product1.getProductId()));
	}
	@Test
	public void testfindByDescriptionAndPrice() {
		assertNotNull(productRepository.findByDescriptionAndPrice(product1.getDescription(),product1.getPrice()));
	}

}
