package com.house.start.domain.dto.Post;


import com.house.start.domain.UploadFile;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Builder
public class PostDto implements Serializable {

    private String contents;
    private String writer;
    private UploadFile uploadFile;
    private LocalDateTime createdDate;
    private int views;
    private int likes;

}
