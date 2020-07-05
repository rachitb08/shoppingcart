package com.bazinga.shoppingcart.service;

import com.bazinga.shoppingcart.exception.BazingaRuntimeException;
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
        return productRepository.findById(id).orElseThrow(() -> new BazingaRuntimeException("Product not found"));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product validateProductExistence(Long productId, Long quantity) {
        Product product = null;
        if (productId != null) {
            product = getProduct(productId);
            if (product == null) {
                throw new BazingaRuntimeException("Product Not Found");
            }
            if (quantity != null && (product.getInventory() - quantity < 0)) {
                throw new BazingaRuntimeException("Requested quantity not available");
            }
        }
        return product;
    }
}
