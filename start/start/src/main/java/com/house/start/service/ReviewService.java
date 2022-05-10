package com.house.start.service;

import com.house.start.domain.Consumer;
import com.house.start.domain.Review;
import com.house.start.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<Review> findReviewsByConsumer (Consumer consumer) {
        return reviewRepository.findByConsumer(consumer);
    }
}
