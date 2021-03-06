package com.house.start.service;

import com.house.start.domain.Item;
import com.house.start.domain.dto.Item.ItemDTO;
import com.house.start.domain.dto.Post.PostDTO;
import com.house.start.repository.QueryDslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final QueryDslRepository dslRepository;
    /**
     * 검색 기능
     * **/

    // 상품 검색
    public List<ItemDTO> findItemByName(String name) {
        return dslRepository.findItemsByName(name);
    }

    // 글 검색
    public List<PostDTO> findPostByContents(String contents) {
        return dslRepository.findPostsByContent(contents);
    }

}
