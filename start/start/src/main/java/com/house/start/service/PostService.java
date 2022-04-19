package com.house.start.service;

import com.house.start.domain.Post;
import com.house.start.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private EntityManager em;
    @Autowired
    PostRepository postRepository;

    public List<Post> findPosts(){
        return postRepository.findAll();
    }
}
