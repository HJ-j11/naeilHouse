package com.house.start.domain.dto.Cart;


import com.house.start.domain.CartItem;
import com.house.start.domain.UploadFile;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CartItemDTO {
    private Long id;
    private String name;
    private int count;
    private int price;
    private UploadFile uploadFile;

    public CartItemDTO(CartItem cartItem) {
        this.id = cartItem.getId();
        this.name = cartItem.getItem().getName();
        this.count = cartItem.getCount();
        this.price = cartItem.getItem().getPrice();
        this.uploadFile = cartItem.getItem().getUploadFile();
    }
}
