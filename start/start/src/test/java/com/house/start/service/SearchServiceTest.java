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
public class SearchServiceTest {
    @Autowired
    QueryDslRepository dslRepository;

    @Test
    public void 상품_검색() throws Exception {
        //Given
        String word = createWord();

        //When
        List<Item> items = dslRepository.findItemsByName(word);

        //Then

    }

    @Test
    public void 게시글_검색() throws Exception {
        String word = createWord();
        List<Post> posts = dslRepository.findPostsByContent(word);
        for(Post post: posts) {

        }
    }

    private String createWord() {
        return "소파";
    }
}