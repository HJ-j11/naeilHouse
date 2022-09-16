package com.house.start.domain.dto.Item;

import com.house.start.domain.Item;
import com.house.start.domain.Review;
import com.house.start.domain.UploadFile;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ItemDTO {
    private Long id;
    private String name;
    private int price;
    private UploadFile uploadFile;
    private String info;
    private List<Review> reviews;


    public ItemDTO(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.uploadFile = item.getUploadFile();
        this.info = item.getInfo();
        this.reviews = item.getReviews();
    }
}
