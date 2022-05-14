package com.house.start.controller.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
public class ReviewForm {
    @NotBlank
    private String contents; // 댓글내용
}
