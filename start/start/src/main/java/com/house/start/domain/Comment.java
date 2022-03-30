package com.house.start.domain;

import javax.persistence.*;

@Entity
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
    private Consumer consumer;

    private String content; // 댓글 내용
}
