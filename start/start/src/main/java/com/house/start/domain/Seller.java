package com.house.start.domain;

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
public class Seller {

    @Id @GeneratedValue
    @Column(name = "seller_id")
    private Long id;

    @OneToMany(mappedBy = "seller")
    private List<Item> items = new ArrayList<>();

    private String name; // 판매자 이름
    private String sId; // 아이디
    private String pwd; // 비밀번호

    private String storeName; // 상호명
    private Boolean isApproved; // 관리자의 승인 여부

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "uploadfile_id")
    private UploadFile uploadFile;

    /**
     * 생성 매소드
     * **/
    @Builder
    public Seller(String name, String sId, String pwd, String storeName) {
        this.name = name;
        this.sId = sId;
        this.pwd = pwd;
        this.storeName = storeName;
    }

}
