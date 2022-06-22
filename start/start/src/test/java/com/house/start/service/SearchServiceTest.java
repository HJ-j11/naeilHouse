package com.house.start.service;

import com.house.start.domain.Item;
import com.house.start.domain.Post;
import com.house.start.repository.QueryDslRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class SearchServiceTest {
    @Autowired
    QueryDslRepository dslRepository;

    @Test
    void 상품_검색() {
        String word = "소파";
        List<Item> items = dslRepository.findItemsByName(word);
        for (Item item: items) {

        }
    }

    @Test
    void 게시글_검색() {
        String word = "소파";
        List<Post> posts = dslRepository.findPostsByContent(word);
        for(Post post: posts) {

        }
    }
}