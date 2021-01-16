package me.hawaijar.shopping.dao;

import me.hawaijar.shopping.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
