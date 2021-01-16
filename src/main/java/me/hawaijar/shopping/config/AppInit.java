package me.hawaijar.shopping.config;

import me.hawaijar.shopping.dao.ProductRepository;
import me.hawaijar.shopping.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AppInit implements CommandLineRunner {
	private final ProductRepository repository;

	@Autowired
	public AppInit(ProductRepository repository) {
		this.repository = repository;
	}
	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();
		repository.saveAll(Arrays.asList(
				new Product("iphone", 200),
				new Product("Mac Book", 2000),
				new Product("Apple Watch", 500)
		));

	}
}
