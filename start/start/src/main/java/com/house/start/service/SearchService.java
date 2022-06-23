package com.house.start.service;

import com.house.start.domain.Item;
import com.house.start.domain.Post;
import com.house.start.repository.ItemRepository;
import com.house.start.repository.PostRepository;
import com.house.start.repository.QueryDslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final ItemRepository itemRepository;
    private final PostRepository postRepository;
    private final QueryDslRepository dslRepository;
    /**
     * 검색 기능
     * **/

    // 상품 검색
    public List<Item> findItemByName(String name) {
        return itemRepository.findItemsByNameLike(name);
    }

    // 글 검색
    public List<Post> findPostByContents(String contents) {
        return postRepository.findPostsByContentsLike(contents);
    }

}
