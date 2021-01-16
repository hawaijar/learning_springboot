package me.hawaijar.shopping.controllers;

import me.hawaijar.shopping.dao.ProductRepository;
import me.hawaijar.shopping.entities.Product;
import me.hawaijar.shopping.entities.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
	public Product getProduct(@PathVariable("id") Integer id) throws ProductNotFoundException {
		return productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException(id));
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Product> insertProduct(@RequestBody Product product) {
		Product p = productRepository.save(product);
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(p.getId())
				.toUri();
		return ResponseEntity.created(location).body(p);
	}
	@PutMapping("{id}")
	public Product updateOrInsertProduct(@PathVariable Integer id,
										 @RequestBody Product newProduct) {
		return productRepository.findById(id).map(product -> {
			product.setName(newProduct.getName());
			product.setPrice(newProduct.getPrice());
			return productRepository.save(product);
		}).orElseGet(() -> {
			newProduct.setId(id);
			return productRepository.save(newProduct);
		});
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
		Optional<Product> existingProduct = productRepository.findById(id);
		if (existingProduct.isPresent()) {
			productRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}


}
