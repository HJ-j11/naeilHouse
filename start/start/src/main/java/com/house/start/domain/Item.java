package com.house.start.domain;
import com.house.start.exception.NotEnoughStockException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id")
    private Seller seller; // 판매자

    private String name; // 상품 이름
    private int price; // 상품 가격
    private int stockQuantity; // 재고 수량
    private String info; // 상품 정보

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "uploadfile_id")
    private UploadFile uploadFile;

    /**
     * 생성 메서드
     * **/
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category; // 상품 카테고리

    // builder 패턴
    @Builder
    public Item(Seller seller, String name, int price, int stockQuantity, String info, UploadFile uploadFile) {
        this.seller = seller;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.uploadFile = uploadFile;
        this.info = info;
    }

    //==연관관계 편의 메서드==//

    public void setSeller(Seller seller) {
        this.seller = seller;
        seller.getItems().add(this);
    }

    public void setCategory(Category category) {
        this.category = category;
        category.getItems().add(this);
    }

    public void setUploadFile(UploadFile uploadFile) {
        this.uploadFile = uploadFile;
        uploadFile.setItem(this);
    }

    //==Getter==//
    public String getCategory() {
        return category.getName();
    }

    //==비즈니스 로직==//
    /**
     * 재고 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
    
}
