package com.house.start.domain;

import com.house.start.domain.entity.BaseTimeEntity;
import com.house.start.domain.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @JoinColumn(name = "post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

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
    
    // 조회수
    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;

    @Builder
    public Post(UploadFile uploadFile, String contents, LocalDateTime postDate, Member member) {
        this.uploadFile = uploadFile;
        this.contents = contents;
        this.member = member;
    }


}
