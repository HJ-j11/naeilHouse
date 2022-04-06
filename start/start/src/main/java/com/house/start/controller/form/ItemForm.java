package com.house.start.controller.form;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemForm {

    private String name;
    private int price;
    private int stockQuantity;
    private String info;
    private String category;
}
