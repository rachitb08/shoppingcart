package com.bazinga.shoppingcart.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RemoveProductFromCartRequest implements Serializable {

    private Long productToRemove;
    private Long userId;
}
