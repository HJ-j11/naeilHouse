package com.house.start.domain.dto.Post;

import com.house.start.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostAdminDTO {
    private Long id;
    private String cId;
    private LocalDateTime postDate;
    private String contents;
    private int like_size;
    private int comments_size;
    private String uploadFileName;

    public PostAdminDTO (Post post) {
        this.id = post.getId();
        this.cId = post.getMember().getUsername();
        this.postDate = post.getPostDate();
        this.contents = post.getContents();
        this.like_size = post.getLikes().size();
        this.comments_size = post.getComments().size();
        this.uploadFileName = post.getUploadFile().getUploadFileName();
    }
}
