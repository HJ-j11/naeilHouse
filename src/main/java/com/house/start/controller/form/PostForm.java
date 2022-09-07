package com.house.start.controller.form;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class PostForm {
    private MultipartFile photo;

    private String contents;

}
