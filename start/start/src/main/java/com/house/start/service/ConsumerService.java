package com.house.start.service;

import com.house.start.domain.Post;
import com.house.start.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ConsumerService {
    @Autowired
    PostRepository postRepository;

    // 커뮤니티 목록
    public List<Post> getAllPost() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }
    
    public Post getOnePost(Long id) {
        Post post = postRepository.getById(id);
        return post;
    }

    // 좋아요
    public void putLikes() {

    }
    
    // 글 작성
    public void postNew(Post post) {
        postRepository.save(post);
    }
}
