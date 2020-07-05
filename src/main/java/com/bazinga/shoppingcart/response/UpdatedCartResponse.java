package com.bazinga.shoppingcart.response;

import com.bazinga.shoppingcart.model.CartLineItem;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UpdatedCartResponse implements Serializable {

    private Double totalCartValue;
    private int totalQuantity;
    private List<CartLineItem> cartLineItems;
    private boolean exception = false;
    private String message;
}
