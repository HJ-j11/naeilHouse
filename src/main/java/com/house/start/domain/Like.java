package com.house.start.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
@NoArgsConstructor
@Table(name = "likes")
public class Like {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post; // 게시물

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer; // 작성자

    @Builder
    public Like(Consumer consumer, Post post) {
        this.consumer = consumer;
        this.post = post;
    }
}
