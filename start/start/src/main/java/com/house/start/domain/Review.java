package com.house.start.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Review {

    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private String content; // 리뷰 내용
    private LocalDateTime reviewDate; // 리뷰 작성 일시


}
