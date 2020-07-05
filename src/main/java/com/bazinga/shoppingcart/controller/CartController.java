package com.bazinga.shoppingcart.controller;

import com.bazinga.shoppingcart.constants.EnumMessage;
import com.bazinga.shoppingcart.dto.AddProductToCartRequest;
import com.bazinga.shoppingcart.dto.RemoveProductFromCartRequest;
import com.bazinga.shoppingcart.exception.BazingaRuntimeException;
import com.bazinga.shoppingcart.response.UpdatedCartResponse;
import com.bazinga.shoppingcart.service.CartLineItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private CartLineItemService cartLineItemService;
    private final String USER_ID = "userId";

    public CartController(CartLineItemService cartLineItemService) {
        this.cartLineItemService = cartLineItemService;
    }

    /**
     *
     * @param addProductToCartRequest
     * @return UpdatedCartResponse
     * @apiNote This api will be called when we have added a product into the cart or we are updating(increasing/decreasing)
     *          the quantity of a product.
     */
    @PostMapping("add")
    public UpdatedCartResponse addProductToCart(@RequestBody AddProductToCartRequest addProductToCartRequest) {

        return cartLineItemService.updateCart(addProductToCartRequest);
    }

    /**
     *
     * @param removeProductFromCartRequest
     * @return UpdatedCartResponse
     * @apiNote This api will remove any particular product from the cart.
     */
    @PostMapping("remove")
    public UpdatedCartResponse removeProductFromCart(@RequestBody RemoveProductFromCartRequest removeProductFromCartRequest) {

        return cartLineItemService.removeProductFromCart(removeProductFromCartRequest);
    }

    /**
     *
     * @param userId
     * @return UpdatedCartResponse
     * @apiNote This api will fetch the latest cart of a user. userId is to be passed in the Header Request.
     */
    @GetMapping("getUserCart")
    public UpdatedCartResponse getCartForUser(
            @RequestHeader(value = USER_ID, required = false) Long userId) {

        if (userId == null) {
            UpdatedCartResponse updatedCartResponse = new UpdatedCartResponse();
            updatedCartResponse.setException(true);
            updatedCartResponse.setMessage(EnumMessage.MANDATORY_PARAMS_MISSING.getErrorMsg());
            return updatedCartResponse;
        }
        return cartLineItemService.fetchCartForUser(userId);
    }

}
