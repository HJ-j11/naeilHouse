package com.house.start.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    private DeliveryStatus deliveryStatus; // 배송 상태 [배송 준비중, 배송 완료]

    @Column(name="review_yn")
    private boolean reviewYn = false;
}
