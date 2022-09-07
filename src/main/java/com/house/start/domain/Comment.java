package com.house.start.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
@NoArgsConstructor
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    /**
     * 단방향
     */
    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer; // 작성자

    private String content; // 댓글 내용

    @Builder
    public Comment(Consumer consumer, String content, Post post) {
        this.consumer = consumer;
        this.content = content;
        this.post = post;
    }
}
