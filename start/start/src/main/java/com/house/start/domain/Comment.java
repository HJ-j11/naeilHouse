package com.house.start.domain;

import com.house.start.domain.entity.Member;
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
    @JoinColumn(name = "member_id")
    private Member member; // 작성자

    private String content; // 댓글 내용

    @Builder
    public Comment(Member member, String content, Post post) {
        this.member = member;
        this.content = content;
        this.post = post;
    }
}
