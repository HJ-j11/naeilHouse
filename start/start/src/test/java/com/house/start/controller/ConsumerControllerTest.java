package com.house.start.controller;

import com.house.start.domain.Post;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


@SpringBootTest
public class ConsumerControllerTest {

    @Test
    public void getPost() {
        final Post post = Post.builder()
                .contents("글쓰기 test")
                .postDate(LocalDateTime.now())
                .build();

    }
    
}