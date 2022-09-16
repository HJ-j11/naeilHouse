package com.house.start.domain.dto.Cart;


import lombok.Getter;

import java.util.List;

@Getter
public class CartDto {
    private Long id;
    private List<CartItemDto> cartItems;

    public int getTotalPrice() {
        int totalPrice = 0;
        for (CartItemDto cartItem:
                cartItems) {
            totalPrice += cartItem.getPrice() * cartItem.getCount();
        }
        return totalPrice;
    }
}
