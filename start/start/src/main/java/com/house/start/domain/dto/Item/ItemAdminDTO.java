package com.house.start.domain.dto.Item;

import com.house.start.domain.Item;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemAdminDTO {
    private String item_uploadFileName;
    private String seller_name;
    private String category;
    private Integer price;
    private String info;
    private Integer stockQuantity;
    private Integer reviews_cnt;

    public ItemAdminDTO(Item item) {
        this.item_uploadFileName = item.getUploadFile().getUploadFileName();
        this.seller_name = item.getMember().getName();
        this.category = item.getCategory();
        this.price = item.getPrice();
        this.info = item.getInfo();
        this.stockQuantity = item.getStockQuantity();
        this.reviews_cnt = item.getReviews().size();
    }
}


