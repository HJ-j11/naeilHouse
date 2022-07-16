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
    @JoinColumn(name = "member_id")
    private Member member; // 작성자

    @Builder
    public Like(Member member, Post post) {
        this.member = member;
        this.post = post;
    }
}
