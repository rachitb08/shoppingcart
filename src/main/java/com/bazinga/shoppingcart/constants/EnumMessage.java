package com.bazinga.shoppingcart.constants;

public enum EnumMessage {

    CART_UPDATED(1000,"Cart updated succcessfully!"),
    UNABLE_TO_REMOVE_PRODUCT(1001,"Product cannot be removed from the cart."),
    PRODUCT_NOT_FOUND(1002,"Product not found."),
    REQUEST_QUANTITY_UNAVAILABLE(1003,"Requested quantity not available."),
    CART_EMPTY(1004,"Cart is Empty."),
    PRODUCT_REMOVED(1005,"Product Removed from Cart."),
    MANDATORY_PARAMS_MISSING(1006,"Mandatory Params are missing.");

    private final int errorCode;
    private final String errorMsg;

    private EnumMessage(int errorCode,String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
