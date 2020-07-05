package com.bazinga.shoppingcart.service;

import com.bazinga.shoppingcart.dto.AddProductToCartRequest;
import com.bazinga.shoppingcart.exception.InventoryNotAvailableException;
import com.bazinga.shoppingcart.exception.ResourceNotFoundException;
import com.bazinga.shoppingcart.model.Product;
import com.bazinga.shoppingcart.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProduct() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product validateProductExistence(AddProductToCartRequest addProductToCartRequest) {
        Product product = null;
        if (addProductToCartRequest.getProductId() != null  && addProductToCartRequest.getQuantity() != null) {
            product = getProduct(addProductToCartRequest.getProductId());
            if (product == null) {
                throw new ResourceNotFoundException("Product Not Found");
            }
            if (product.getInventory() - addProductToCartRequest.getQuantity() < 0) {
                throw new InventoryNotAvailableException("Requested quantity not available");
            }
        }
        return product;
    }
}
