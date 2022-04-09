package com.house.start.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter @Setter
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

    private OrderStatus orderStatus; // 주문 상태 [주문, 취소, 완료]

    /**
     * 주문 취소
     */
    public void cancel() {
        if (delivery.getDeliveryStatus() == DeliveryStatus.COMPLETE) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        // 주문 상태 변경
        this.setOrderStatus(OrderStatus.CANCEL);
    }

}
