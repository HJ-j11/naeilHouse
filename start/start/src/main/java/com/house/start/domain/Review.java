package com.house.start.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter@Setter
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

    //==연관관계 편의 메서드==//
    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
        consumer.getReviews().add(this);
    }
}
