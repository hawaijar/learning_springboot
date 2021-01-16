package me.hawaijar.shopping.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.Validator;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductTest {
	@Autowired
	private Validator validator;

	@Test
	void autoWiringWorked() {
		assertNotNull(validator);
	}
}
