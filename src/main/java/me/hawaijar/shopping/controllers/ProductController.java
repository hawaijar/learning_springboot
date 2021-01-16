package me.hawaijar.shopping.controllers;

import me.hawaijar.shopping.dao.ProductRepository;
import me.hawaijar.shopping.entities.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Optional;

@Controller // subclass of @Component
@RequestMapping("/products")
public class ProductController {
	private final ProductRepository productRepository;

	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	// In Spring, there's only one servlet - DispatcherServlet.
	// All Http requests go through that Servlet.
	// Below, all attributes added to the model is added to Http Request, before passing the model
	// to the View.
	// 'Model' is an interface. Spring injects an instance of it here.
	@GetMapping
	public String getProducts(Model model) {
		model.addAttribute("products", productRepository.findAll());
		// Here, we're using Thymeleaf View Resolver
		// So, the below will map to src/main/resources/templates/products.html
		return "products";
	}
	@GetMapping("{id}")
	public String getProduct(@PathVariable Integer id, Model model){
		Optional<Product> optional = productRepository.findById(id);
//		if(optional.isPresent()) {
//			model.addAttribute("product", optional.get());
//		}
//		else {
//			throw new Exception("Product Not Found");
//		}
		optional.ifPresent(product -> model.addAttribute("products", product));
		return "products";
	}
}
