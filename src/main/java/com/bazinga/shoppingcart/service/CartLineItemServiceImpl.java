package com.bazinga.shoppingcart.service;

import com.bazinga.shoppingcart.dto.AddProductToCartRequest;
import com.bazinga.shoppingcart.model.CartLineItem;
import com.bazinga.shoppingcart.model.Product;
import com.bazinga.shoppingcart.repository.CartLineItemRepository;
import com.bazinga.shoppingcart.response.UpdatedCartResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartLineItemServiceImpl implements CartLineItemService {

    private ProductService productService;
    private CartLineItemRepository cartLineItemRepository;

    public CartLineItemServiceImpl(ProductService productService, CartLineItemRepository cartLineItemRepository) {
        this.productService = productService;
        this.cartLineItemRepository = cartLineItemRepository;
    }

    @Override
    public UpdatedCartResponse updateCart(AddProductToCartRequest addProductToCartRequest) {
        UpdatedCartResponse updatedCartResponse = null;
        try {

            Product product = productService.validateProductExistence(addProductToCartRequest);

            createOrUpdateCart(addProductToCartRequest, product);

            return fetchCartForUser(addProductToCartRequest);
        } catch (Exception e) {

            updatedCartResponse = new UpdatedCartResponse();
            updatedCartResponse.setException(false);
            updatedCartResponse.setMessage(e.getMessage());
        }
        return updatedCartResponse;
    }

    private UpdatedCartResponse fetchCartForUser(AddProductToCartRequest addProductToCartRequest) {
        UpdatedCartResponse updatedCartResponse = new UpdatedCartResponse();
        List<CartLineItem> cartLineItems = cartLineItemRepository.findAllByUserId(addProductToCartRequest.getUserId());
        if (CollectionUtils.isNotEmpty(cartLineItems)) {
            updatedCartResponse.setCartLineItems(cartLineItems);
            updatedCartResponse.setTotalQuantity(cartLineItems.size());
            double totalCartValue = 0D;
            for (CartLineItem cli : cartLineItems) {
                totalCartValue += cli.getProduct().getPrice() * cli.getQuantity();
            }
            updatedCartResponse.setTotalCartValue(totalCartValue);
            updatedCartResponse.setException(false);
            updatedCartResponse.setMessage("Cart updated succcessfully!");
        } else {
            updatedCartResponse.setException(true);
            updatedCartResponse.setMessage("Cart is Empty");
        }
        return updatedCartResponse;
    }

    public void createOrUpdateCart(AddProductToCartRequest addProductToCartRequest, Product product) {

        CartLineItem cartLineItem = cartLineItemRepository.findByUserIdAndProduct(addProductToCartRequest.getUserId(),
                product);

        if (cartLineItem == null) {
            cartLineItem = new CartLineItem();
            cartLineItem.setProduct(product);
            cartLineItem.setUserId(addProductToCartRequest.getUserId());
        }

        cartLineItem.setQuantity(addProductToCartRequest.getQuantity());
        cartLineItemRepository.save(cartLineItem);
    }
}
