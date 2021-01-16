package me.hawaijar.shopping.controllers;

import me.hawaijar.shopping.dao.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // subclass of @Component
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
	@GetMapping("/products")
	public String showProducts(Model model) {
		model.addAttribute("products", productRepository.findAll());
		// Here, we're using Thymeleaf View Resolver
		// So, the below will map to src/main/resources/templates/products.html
		return "products";
	}
}
