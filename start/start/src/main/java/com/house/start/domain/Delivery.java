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
    private OrderItem orderItem;

    private String address;

    private DeliveryStatus deliveryStatus; // 배송 상태 [배송 준비중, 배송 완료]

    // 생성 메서드
    // 잠시 주석 처리 해놨습니다!
    /*@Builder
    public Delivery(DeliveryStatus deliveryStatus, Order order) {
        this.order = order;
        this.deliveryStatus = deliveryStatus;
    }*/

}
