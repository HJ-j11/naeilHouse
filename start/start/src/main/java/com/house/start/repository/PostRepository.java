package com.house.start.repository;


import com.house.start.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT COUNT(l.id) FROM Post p LEFT OUTER JOIN p.likes l where p.id = :ID")
    Long countByLikes(@Param(value="ID") Long id);

    @Query("SELECT COUNT(p.id) FROM Post p LEFT OUTER JOIN p.comments p where p.id = :ID")
    Long countByComments(@Param(value="ID") Long id);
}
