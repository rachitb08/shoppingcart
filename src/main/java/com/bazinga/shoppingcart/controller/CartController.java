package com.bazinga.shoppingcart.controller;

import com.bazinga.shoppingcart.dto.AddProductToCartRequest;
import com.bazinga.shoppingcart.dto.RemoveProductFromCartRequest;
import com.bazinga.shoppingcart.response.UpdatedCartResponse;
import com.bazinga.shoppingcart.service.CartLineItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private CartLineItemService cartLineItemService;

    public CartController(CartLineItemService cartLineItemService) {
        this.cartLineItemService = cartLineItemService;
    }

    @PostMapping("add")
    public UpdatedCartResponse addProductToCart(@RequestBody AddProductToCartRequest addProductToCartRequest) {

        return cartLineItemService.updateCart(addProductToCartRequest);
    }

    @PostMapping("remove")
    public UpdatedCartResponse removeProductFromCart(@RequestBody RemoveProductFromCartRequest removeProductFromCartRequest) {

        return cartLineItemService.removeProductFromCart(removeProductFromCartRequest);
    }

}
