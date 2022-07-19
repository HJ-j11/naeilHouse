package com.house.start.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter@Setter
@NoArgsConstructor
public class Review {

    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private String content; // 리뷰 내용
    private LocalDateTime reviewDate; // 리뷰 작성 일시

    //==연관관계 편의 메서드==//
    public void setConsumer(Member member) {
        this.member = member;
        member.getReviews().add(this);
    }
}