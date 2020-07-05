package com.bazinga.shoppingcart;

import com.bazinga.shoppingcart.model.Product;
import com.bazinga.shoppingcart.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShoppingcartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingcartApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ProductService productService) {
		return args -> {
			productService.save(new Product("TV Set", 300.00, 100L));
			productService.save(new Product("Game Console", 200.00, 100L));
			productService.save(new Product( "Sofa", 100.00, 100L));
			productService.save(new Product( "Icecream", 5.00, 100L));
			productService.save(new Product( "Beer", 3.00, 100L));
			productService.save(new Product( "Phone", 500.00, 100L));
			productService.save(new Product( "Watch", 30.00, 100L));
		};
	}

}
