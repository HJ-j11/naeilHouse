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
public class Consumer {

    @Id @GeneratedValue
    @Column(name = "consumer_id")
    private Long id;

    @OneToMany(mappedBy = "consumer")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "consumer")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "consumer")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "consumer")
    private List<Like> likes = new ArrayList<>();

    private String name; // 소비자 이름
    private int point; // 포인트 금액

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "uploadfile_id")
    private UploadFile uploadFile;

    @Column(name = "id")
    private String cId; // 아이디
    private String pwd; // 비밀번호
    private String photo;
    
    /**
     * 생성 매소드
     * **/
    @Builder
    public Consumer(String cId, String pwd, String name) {
        this.cId = cId;
        this.pwd = pwd;
        this.name = name;
    }
    
//    private String photo;

    //==연관관계 편의 메서드==//
    public void setUploadFile(UploadFile uploadFile) {
        this.uploadFile = uploadFile;
        uploadFile.setConsumer(this);
    }

}
