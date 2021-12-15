package com.example.demo;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class DemoApplicationTests {
	@Autowired
	ProductRepo productRepo;

	@Test
	@Order(1)
	public void test(){
		Product product = new Product();
		product.setId(23L);
		product.setName("Beras");
		product.setJumlah(10);
		product.setMerek("Pandan Wangi");
		product.setHarga(10000);
		productRepo.save(product);
		Assertions.assertNotNull(productRepo.findById(23L).get());

	}

	@Test
	@Order(2)
	public void testReadAll () {
		List list = productRepo.findAll();
		assertThat(list).size().isGreaterThan(0);
	}

	@Test
	@Order(3)
	public void testRead () {
		Product product = productRepo.findById(22L).get();
		Assertions.assertEquals("Beras", product.getName());
	}

	@Test
	@Order(4)
	public void testUpdate () {
		Product product = productRepo.findById(22L).get();
		product.setHarga(800);
		productRepo.save(product);
		Assertions.assertNotEquals(10000, productRepo.findById(22L).get().getHarga());
	}
	@Test
	@Order(5)
	public void testDelete () {
		productRepo.deleteById(10L);
		assertThat(productRepo.existsById(10L)).isFalse();
	}


}
