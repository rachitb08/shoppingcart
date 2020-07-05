package com.bazinga.shoppingcart.service;

import com.bazinga.shoppingcart.dto.AddProductToCartRequest;
import com.bazinga.shoppingcart.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    Product getProduct(Long id);

    Product save(Product product);

    Product validateProductExistence(AddProductToCartRequest addProductToCartRequest);
}
