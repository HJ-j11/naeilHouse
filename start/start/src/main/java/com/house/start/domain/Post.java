package com.house.start.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    @JoinColumn(name = "post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

    @OneToMany(mappedBy = "post")
    private List<Like> likes = new ArrayList<>();

    // 이미지 업로드
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "uploadfile_id")
    private UploadFile uploadFile;

    // 글 작성
    private String contents;
    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();
    private LocalDateTime postDate; // 게시물 작성일시
    
    // 조회수
    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;
    public Long countLikes() {
        return Long.valueOf(this.likes.size());
    }


    @Builder
    public Post(UploadFile uploadFile, String contents, LocalDateTime postDate, Consumer consumer) {
        this.uploadFile = uploadFile;
        this.contents = contents;
        this.postDate = postDate;
        this.consumer = consumer;
    }


}
