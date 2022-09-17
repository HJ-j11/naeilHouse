package com.house.start.repository;


import com.house.start.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT COUNT(l.id) FROM Post p LEFT OUTER JOIN p.likes l where p.id = :ID")
    Long countByLikes(@Param(value="ID") Long id);

    @Query("SELECT COUNT(p2.id) FROM Post p1 LEFT OUTER JOIN p1.comments p2 where p1.id = :ID")
    Long countByComments(@Param(value="ID") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.view = p.view + 1 WHERE p.id=:ID")
    void updateView(@Param(value="ID") Long id);

    List<Post> findPostsByContentsLike(String contents);

    //Pagind
    Page<Post> findByOrderByIdDesc(Pageable pageable);

}
