package com.house.start.service;

import com.house.start.domain.Consumer;
import com.house.start.domain.Like;
import com.house.start.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;

    public List<Like> findLikesByConsumer(Consumer consumer) {
        return likeRepository.findByConsumer(consumer);
    }
}
