package me.hawaijar.shopping.controllers;

import me.hawaijar.shopping.dao.ProductRepository;
import me.hawaijar.shopping.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
	private final ProductRepository productRepository;

	@Autowired
	public ProductRestController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@GetMapping
	public List<Product> getAllProducts(
			@RequestParam(required = false) Double minimumPrice
	) {
		if(minimumPrice != null) {
			return productRepository.findAllByPriceGreaterThanEqual(minimumPrice);
		}
		return productRepository.findAll();
	}
	@GetMapping("{id}")
	public Product getProduct(@PathVariable("id") Integer id) throws Exception {
		return productRepository.findById(id)
				.orElseThrow(() -> new Exception("id not found"));
	}
}
