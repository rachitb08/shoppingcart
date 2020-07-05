package com.bazinga.shoppingcart.repository;

import com.bazinga.shoppingcart.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
