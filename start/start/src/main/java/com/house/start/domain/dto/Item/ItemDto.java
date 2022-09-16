package com.house.start.domain.dto.Item;

import com.house.start.domain.Review;
import com.house.start.domain.UploadFile;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ItemDto {

    private String name;
    private String price;
    private UploadFile uploadFile;
    private String itemInfo;
    private List<Review> reviews;

}
