package com.house.start.domain.dto.Order.OrderItem;

import com.house.start.domain.OrderItem;
import com.house.start.domain.UploadFile;
import lombok.Getter;

@Getter
public class OrderItemDTO {
    private UploadFile uploadFile;
    private String name;
    private int price;

    public OrderItemDTO(OrderItem orderItem) {
        this.uploadFile = orderItem.getItem().getUploadFile();
        this.name = orderItem.getItem().getName();
        this.price = orderItem.getOrderPrice();
    }
}
