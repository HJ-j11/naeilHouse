package com.house.start.repository;

import static com.house.start.domain.QPost.*;
import static com.house.start.domain.QItem.*;
import static com.house.start.domain.QComment.*;
import static com.house.start.domain.QPost.*;

import com.house.start.domain.Comment;
import com.house.start.domain.Item;
import com.house.start.domain.Post;
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

    public List<Post> findPostsByContent(String word) {
        return jpaQueryFactory.select(post)
                .where(post.contents.like(word))
                .fetch();
    }

    public List<Item> findItemsByName(String word) {
        return jpaQueryFactory.select(item)
                .where(item.name.like(word))
                .fetch();
    }

}
