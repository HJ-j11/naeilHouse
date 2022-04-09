package com.house.start.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate; // 주문 일시

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; // 주문 상태 [주문, 취소, 완료]

    // setDelivery
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    /*비즈니스로직*/
    

}
