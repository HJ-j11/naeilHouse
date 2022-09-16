package com.house.start.domain.dto.Cart;


import lombok.Getter;

import java.util.List;

@Getter
public class CartDto {
    private Long id;
    private List<CartItemDTO> cartItems;

    public int getTotalPrice() {
        int totalPrice = 0;
        for (CartItemDTO cartItem:
                cartItems) {
            totalPrice += cartItem.getPrice() * cartItem.getCount();
        }
        return totalPrice;
    }
}
