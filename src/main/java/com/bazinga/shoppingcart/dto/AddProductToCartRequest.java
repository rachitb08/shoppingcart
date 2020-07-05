package com.bazinga.shoppingcart.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AddProductToCartRequest implements Serializable {

    private Long productId;
    private Long userId;
    private Long quantity;
}
