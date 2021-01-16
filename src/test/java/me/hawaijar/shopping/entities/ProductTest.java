package me.hawaijar.shopping.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductTest {
	@Autowired
	private Validator validator;

	@Test
	void autoWiringWorked() {
		assertNotNull(validator);
	}
	@Test
	void nameCanNotBeBlank() {
		Product product = new Product("", 10.0);
		Set<ConstraintViolation<Product>> violations = validator.validate(product);
		assertEquals(1, violations.size());

		// Extract violation using set.iterator(); alternative using streams in price test
		ConstraintViolation<Product> violation = violations.iterator().next();
		assertEquals("A name is required", violation.getMessage());
	}

	@Test
	void priceMustBeGEZero() {
		Product product = new Product("name", 1);
		Set<ConstraintViolation<Product>> violations = validator.validate(product);
		assertEquals(1, violations.size());

		Optional<ConstraintViolation<Product>> optionalViolation = violations.stream().findFirst();
		assertTrue(optionalViolation.isPresent());
		assertEquals("Price must be greater than zero", optionalViolation.get().getMessage());
	}
}
