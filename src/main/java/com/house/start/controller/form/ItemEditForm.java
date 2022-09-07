package com.house.start.controller.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ItemEditForm {

    private String name;
    private int price;
    private String info;
    private int stockQuantity;
    private String category;
    private String storeFileName;
}
