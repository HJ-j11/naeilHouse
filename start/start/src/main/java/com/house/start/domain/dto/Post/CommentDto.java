package com.house.start.domain.dto.Post;


import lombok.Getter;

@Getter
public class CommentDto {
    private Long id;
    private String memberName;
    private String contents;
}
