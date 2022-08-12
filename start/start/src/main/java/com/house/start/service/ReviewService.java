package com.house.start.service;

import com.house.start.domain.Item;
import com.house.start.domain.entity.Member;
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

    /**
     * 소비자로 모든 review 조회
     */
    public List<Review> findReviewsByConsumer (Member member) {
        return reviewRepository.findByMember(member);
    }

    /**
     * review 저장
     */
    public Review saveReview(Member member, Item item, String content) {
        Review review = new Review();
        review.setMember(member);
        review.setItem(item);
        review.setContent(content);
        reviewRepository.save(review);
        return review;
    }
}
