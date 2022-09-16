package com.house.start.domain.dto.Post;


import com.house.start.domain.Like;
import com.house.start.domain.UploadFile;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class PostDto implements Serializable {
    private Long id;
    private String contents;
    private String writer;
    private UploadFile uploadFile;
    private LocalDateTime createdDate;
    private int views;
    private List<Like> likes ;
    private List<CommentDto> comments;

}
