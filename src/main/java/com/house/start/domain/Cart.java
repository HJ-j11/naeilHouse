package com.house.start.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Cart {

    @Id @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    @Builder
    public Cart(Consumer consumer) {
        this.consumer = consumer;
    }

    // 장바구니에 상품 추가
    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setCart(this);
    }

    // 전체 가격 메소드
    public int getTotalPrice() {
        int totalPrice = 0;
        for (CartItem cartItem:
             cartItems) {
            totalPrice += cartItem.getItem().getPrice() * cartItem.getCount();
        }
        return totalPrice;
    }

    public void removeCartItem() {
        cartItems.clear();
    }
}
