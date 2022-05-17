package com.house.start.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Cart {

    @Id @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    @Builder
    public Cart(Consumer consumer) {
        this.consumer = consumer;
    }

    // 장바구니에 상품 추가
    public void addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
    }
}
