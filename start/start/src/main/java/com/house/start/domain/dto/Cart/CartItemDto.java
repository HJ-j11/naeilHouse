package com.house.start.domain.dto.Cart;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartItemDto {
    private String name;
    private String count;
    private long price;
}
