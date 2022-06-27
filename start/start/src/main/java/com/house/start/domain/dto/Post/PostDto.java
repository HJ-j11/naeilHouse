package com.house.start.domain.dto.Post;


import com.house.start.domain.UploadFile;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDto {

    private String contents;
    private UploadFile uploadFile;
    private LocalDateTime postDate;
    private int view;
    private int likes;

}
