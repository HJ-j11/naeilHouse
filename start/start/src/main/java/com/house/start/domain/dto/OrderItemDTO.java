package com.house.start.domain.dto;

import com.house.start.domain.Delivery;
import com.house.start.domain.Item;
import com.house.start.domain.OrderItem;
import com.house.start.domain.OrderItemStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemDTO {
    private Long id;
    private Item item;
    private int orderPrice;
    private int count;
    private Delivery delivery;
    private OrderItemStatus orderItemStatus;

    @Builder
    public OrderItemDTO(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.item = orderItem.getItem();
        this.orderPrice = orderItem.getOrderPrice();
        this.count = orderItem.getCount();
        this.delivery = orderItem.getDelivery();
        this.orderItemStatus = orderItem.getOrderItemStatus();
    }
}
