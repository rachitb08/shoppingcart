package com.bazinga.shoppingcart.controller;

import com.bazinga.shoppingcart.model.Product;
import com.bazinga.shoppingcart.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * @apiNote This api will retrieve list of products.
     * @return List of Products
     */
    @GetMapping(value = { "", "/" })
    public List<Product> getProducts() {
        return productService.getAllProduct();
    }
}
