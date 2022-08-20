package com.house.start.service;

import com.house.start.domain.Post;
import com.house.start.domain.dto.Post.PostAdminDTO;
import com.house.start.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private EntityManager em;
    private final PostRepository postRepository;

    public List<Post> findPosts(){
        return postRepository.findAll();
    }

    public Page<PostAdminDTO> findPostDTO(Pageable pageable) {
        return postRepository.findByOrderByIdDesc(pageable)
                .map(PostAdminDTO::new);
    }
    /**
     * 전체 게시글 수
     */
}
