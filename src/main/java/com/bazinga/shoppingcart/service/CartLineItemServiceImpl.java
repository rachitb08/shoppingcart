package com.bazinga.shoppingcart.service;

import com.bazinga.shoppingcart.dto.AddProductToCartRequest;
import com.bazinga.shoppingcart.dto.RemoveProductFromCartRequest;
import com.bazinga.shoppingcart.exception.BazingaRuntimeException;
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

            Product product = productService.validateProductExistence(addProductToCartRequest.getProductId(), addProductToCartRequest.getQuantity());

            createOrUpdateCart(addProductToCartRequest.getUserId(), addProductToCartRequest.getQuantity(), product);

            return fetchCartForUser(addProductToCartRequest.getUserId());
        } catch (Exception e) {

            updatedCartResponse = new UpdatedCartResponse();
            updatedCartResponse.setException(false);
            updatedCartResponse.setMessage(e.getMessage());
        }
        return updatedCartResponse;
    }

    @Override
    public UpdatedCartResponse removeProductFromCart(RemoveProductFromCartRequest removeProductFromCartRequest) {
        UpdatedCartResponse updatedCartResponse = null;
        try {

            Product product = productService.validateProductExistence(removeProductFromCartRequest.getProductToRemove(), null);

            removeProductFromCart(removeProductFromCartRequest.getUserId(), product);

            return fetchCartForUser(removeProductFromCartRequest.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
            updatedCartResponse = new UpdatedCartResponse();
            updatedCartResponse.setException(false);
            updatedCartResponse.setMessage(e.getMessage());
        }
        return updatedCartResponse;
    }

    public void removeProductFromCart(Long userId, Product product) {

        int cartLineItemDeleted = cartLineItemRepository.deleteByUserIdAndProduct(userId, product);
        if (cartLineItemDeleted == 0) {
            throw new BazingaRuntimeException("Product cannot be removed from the cart.");
        }
    }

    private UpdatedCartResponse fetchCartForUser(Long userId) {
        UpdatedCartResponse updatedCartResponse = new UpdatedCartResponse();
        List<CartLineItem> cartLineItems = cartLineItemRepository.findAllByUserId(userId);
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

    public void createOrUpdateCart(Long userId, Long quantity, Product product) {

        CartLineItem cartLineItem = cartLineItemRepository.findByUserIdAndProduct(userId, product);

        if (cartLineItem == null) {
            cartLineItem = new CartLineItem();
            cartLineItem.setProduct(product);
            cartLineItem.setUserId(userId);
        }

        cartLineItem.setQuantity(quantity);
        cartLineItemRepository.save(cartLineItem);
    }
}
