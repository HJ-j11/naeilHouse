package com.house.start.repository;

import static com.house.start.domain.QPost.*;
import static com.house.start.domain.QItem.*;

import com.house.start.domain.Item;
import com.house.start.domain.dto.Item.ItemDTO;
import com.house.start.domain.dto.Post.PostDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 검색 기능
     * **/

    public List<PostDTO> findPostsByContent(String word) {
        return jpaQueryFactory.from(post)
                .select(Projections.bean(PostDTO.class, post.contents, post.uploadFile, post.postDate))
                .where(post.contents.like(word))
                .fetch();
    }

    public List<ItemDTO> findItemsByName(String word) {
        return jpaQueryFactory.from(item)
                .select(Projections.bean(ItemDTO.class, item.name, item.price))
                .where(item.name.like(word))
                .fetch();
    }

}
