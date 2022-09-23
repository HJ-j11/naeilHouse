package com.house.start.service;

import com.house.start.domain.Like;
import com.house.start.domain.entity.Member;
import com.house.start.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;

    public List<Like> findLikesByConsumer(Member member) {
        return likeRepository.findByMember(member);
    }
}
