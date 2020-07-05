package com.bazinga.shoppingcart.service;

import com.bazinga.shoppingcart.constants.EnumMessage;
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
public class CartLineItemServiceImpl implements CartLineItemService {

    private ProductService productService;
    private CartLineItemRepository cartLineItemRepository;

    public CartLineItemServiceImpl(ProductService productService, CartLineItemRepository cartLineItemRepository) {
        this.productService = productService;
        this.cartLineItemRepository = cartLineItemRepository;
    }

    @Override
    @Transactional
    public UpdatedCartResponse updateCart(AddProductToCartRequest addProductToCartRequest) {
        UpdatedCartResponse updatedCartResponse = null;
        try {

            if (addProductToCartRequest == null ||  addProductToCartRequest.getUserId() == null
                    || addProductToCartRequest.getProductId() == null) {
                updatedCartResponse = new UpdatedCartResponse();
                updatedCartResponse.setException(true);
                updatedCartResponse.setMessage(EnumMessage.MANDATORY_PARAMS_MISSING.getErrorMsg());
                return updatedCartResponse;
            }

            Product product = productService.validateProductExistence(addProductToCartRequest.getProductId(), addProductToCartRequest.getQuantity());

            createOrUpdateCart(addProductToCartRequest.getUserId(), addProductToCartRequest.getQuantity(), product);

            updatedCartResponse = fetchCartForUser(addProductToCartRequest.getUserId());
            updatedCartResponse.setMessage(EnumMessage.CART_UPDATED.getErrorMsg());
        } catch (Exception e) {

            updatedCartResponse = new UpdatedCartResponse();
            updatedCartResponse.setException(false);
            updatedCartResponse.setMessage(e.getMessage());
        }
        return updatedCartResponse;
    }

    @Override
    @Transactional
    public UpdatedCartResponse removeProductFromCart(RemoveProductFromCartRequest removeProductFromCartRequest) {
        UpdatedCartResponse updatedCartResponse = null;
        try {

            if (removeProductFromCartRequest == null ||  removeProductFromCartRequest.getUserId() == null
                    || removeProductFromCartRequest.getProductToRemove() == null) {
                updatedCartResponse = new UpdatedCartResponse();
                updatedCartResponse.setException(true);
                updatedCartResponse.setMessage(EnumMessage.MANDATORY_PARAMS_MISSING.getErrorMsg());
                return updatedCartResponse;
            }

            Product product = productService.validateProductExistence(removeProductFromCartRequest.getProductToRemove(), null);

            removeProductFromCart(removeProductFromCartRequest.getUserId(), product);

            updatedCartResponse = fetchCartForUser(removeProductFromCartRequest.getUserId());
            updatedCartResponse.setMessage(EnumMessage.PRODUCT_REMOVED.getErrorMsg());
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
            throw new BazingaRuntimeException(EnumMessage.UNABLE_TO_REMOVE_PRODUCT.getErrorMsg());
        }
    }

    @Override
    public UpdatedCartResponse fetchCartForUser(Long userId) {
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
