package com.house.start.controller.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
public class ReviewForm {

    @NotBlank
    private String content; // 댓글내용
}
