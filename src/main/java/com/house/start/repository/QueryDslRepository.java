package com.house.start.repository;

import static com.house.start.domain.QPost.*;
import static com.house.start.domain.QItem.*;
import static com.house.start.domain.QLike.*;
import static com.house.start.domain.entity.QMember.*;

import com.house.start.domain.Like;
import com.house.start.domain.dto.Item.ItemDTO;
import com.house.start.domain.dto.Post.PostDto;
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

    public List<PostDto> findPostsByContent(String word) {
        return jpaQueryFactory.from(post)
                .select(Projections.bean(PostDto.class, post.contents, post.uploadFile, post.createdDate))
                .where(post.contents.like(word))
                .fetch();
    }

    public List<ItemDTO> findItemsByName(String word) {
        return jpaQueryFactory.from(item)
                .select(Projections.bean(ItemDTO.class, item.name, item.price))
                .where(item.name.like(word))
                .fetch();
    }

    public Long checkLikeOnPost(Long postId, Long memberId) {
        return jpaQueryFactory.from(like)
                .select(like.id)
                .where(like.post.id.eq(postId))
                .where(like.member.id.eq(memberId))
                .fetchOne();
    }

}
