package com.house.start.controller.form;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentForm {

    private Long id;
    private String content;
    private Long postId;

}
