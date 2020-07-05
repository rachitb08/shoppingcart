package com.bazinga.shoppingcart.repository;

import com.bazinga.shoppingcart.model.CartLineItem;
import com.bazinga.shoppingcart.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartLineItemRepository extends CrudRepository<CartLineItem, Long> {

    CartLineItem findByUserIdAndProduct(Long userId, Product product);

    List<CartLineItem> findAllByUserId(Long userId);
}
