package com.house.start.domain.dto.Order.OrderItem;

import com.house.start.domain.Delivery;
import com.house.start.domain.Item;
import com.house.start.domain.OrderItem;
import com.house.start.domain.OrderItemStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderOrderItemAdminDTO {
    private Long id;
    private Item item;
    private int orderPrice;
    private int orderCount;
    private Delivery delivery;
    private OrderItemStatus orderItemStatus;

    @Builder
    public OrderOrderItemAdminDTO(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.item = orderItem.getItem();
        this.orderPrice = orderItem.getOrderPrice();
        this.orderCount = orderItem.getCount();
        this.delivery = orderItem.getDelivery();
        this.orderItemStatus = orderItem.getOrderItemStatus();
    }
}
