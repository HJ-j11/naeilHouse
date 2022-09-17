package com.house.start.domain.dto.Post;


import com.house.start.domain.Comment;
import com.house.start.domain.Like;
import com.house.start.domain.Post;
import com.house.start.domain.UploadFile;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostDto implements Serializable {
    private Long id;
    private String contents;
    private String writer;
    private UploadFile uploadFile;
    private LocalDateTime createdDate;
    private int views;
    private List<Like> likes;
    private List<Comment> comments;

    public PostDto(Post post) {
        this.id = post.getId();
        this.contents = post.getContents();
        this.writer = post.getMember().getName();
        this.uploadFile = post.getUploadFile();
        this.createdDate = post.getCreatedDate();
        this.views = post.getView();
        this.likes = post.getLikes();
        this.comments = post.getComments();
    }

}
