package me.hawaijar.shopping.dao;

import me.hawaijar.shopping.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
	List<Product> findAllByPriceGreaterThanEqual(double amount);
}
