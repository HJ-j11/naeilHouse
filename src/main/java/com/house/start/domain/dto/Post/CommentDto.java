package com.house.start.domain.dto.Post;


import com.house.start.domain.Comment;
import lombok.Getter;

@Getter
public class CommentDto {
    private Long id;
    private String memberName;
    private String contents;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.memberName = comment.getMember().getName();
        this.contents = comment.getContent();
    }
}
