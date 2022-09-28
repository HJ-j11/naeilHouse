package com.house.start.domain.dto.Member.Consumer.OrderItem;

import com.house.start.domain.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ConsumerMypageOrderOrderItemDTO {
    private String uploadFileName;
    private String member_storeName;
    private String item_name;
    private int orderPrice;
    private String deliveryStatus;
    private String orderItemStatus;
    private Long id;

    public ConsumerMypageOrderOrderItemDTO (OrderItem orderItem) {
        this.uploadFileName = orderItem.getItem().getUploadFile().getStoreFileName();
        this.member_storeName = orderItem.getItem().getMember().getStoreName();
        this.item_name = orderItem.getItem().getName();
        this.orderPrice = orderItem.getOrderPrice();
        this.deliveryStatus = orderItem.getDelivery().getDeliveryStatus().toString();
        this.orderItemStatus = orderItem.getOrderItemStatus().toString();
        this.id = orderItem.getId();
    }
}
