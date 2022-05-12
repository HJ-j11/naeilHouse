package com.house.start.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    private String address;

    private DeliveryStatus deliveryStatus; // 배송 상태 [배송 준비중, 배송 완료]

    @Column(name="review_yn")
    private boolean reviewYn = false;
    
    // 생성 메서드

    @Builder
    public Delivery(DeliveryStatus deliveryStatus, boolean reviewYn, Order order) {
        this.order = order;
        this.deliveryStatus = deliveryStatus;
        this.reviewYn = reviewYn;
    }

}
