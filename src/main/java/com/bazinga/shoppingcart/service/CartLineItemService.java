package com.bazinga.shoppingcart.service;

import com.bazinga.shoppingcart.dto.AddProductToCartRequest;
import com.bazinga.shoppingcart.response.UpdatedCartResponse;

public interface CartLineItemService {

    UpdatedCartResponse updateCart(AddProductToCartRequest addProductToCartRequest);
}
