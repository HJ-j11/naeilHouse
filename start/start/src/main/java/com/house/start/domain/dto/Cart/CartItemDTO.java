package com.house.start.domain.dto.Cart;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartItemDTO {
    private Long id;
    private String name;
    private Long count;
    private Long price;
}
