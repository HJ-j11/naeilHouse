package com.house.start.controller;

import com.house.start.controller.form.CommentForm;
import com.house.start.controller.form.PostForm;
import com.house.start.controller.session.SessionConstants;
import com.house.start.domain.*;
import com.house.start.domain.dto.Post.PostDto;
import com.house.start.domain.entity.Member;
import com.house.start.file.FileStore;
import com.house.start.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import com.house.start.service.PostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
public class PostController {
    private final ConsumerService consumerService;
    private final PostService postService;
    private final FileStore fileStore;
    private Logger logger = LoggerFactory.getLogger(ConsumerController.class);
    
    /**
     * 포스트
     * **/
    
    //게시글 목록
    @GetMapping("/community")
    public String getAllPost(Model model) {
        log.info("--- PostController - /community -----------------------------------------");

        List<PostDto> posts = consumerService.getAllPost();
        model.addAttribute("postList", posts);
        return "consumer/post_list";
    }

    // 작성 글 조회
    @GetMapping("/community/{id}")
    public String getOnePost(@PathVariable Long id,
                             @AuthenticationPrincipal Member loginMember,
                             Model model, HttpServletRequest request) {

        Post post = consumerService.getOnePost(id);
        List<Like> likes = post.getLikes();
        // 글 조회수 update
        consumerService.updateView(id);

        boolean checkLike = postService.checkLikeOnPost(id, loginMember.getId());

        model.addAttribute("liked", checkLike);
        model.addAttribute("post", post);
        model.addAttribute("likes", likes);
        model.addAttribute("comments", post.getComments());

        return "consumer/consumer_postDetail";
    }


    // 글 작성 페이지
    @GetMapping("/community/new")
    public String getNewPost(@AuthenticationPrincipal Member member,
                             Model model) {
        log.info("--- PostController - /community/new -----------------------------------------");

        // 세션에 회원 데이터가 없으면 예외처리
        if(member == null) {
            return "redirect:/login";
        }

        model.addAttribute("post", new PostForm());
        return "consumer/consumer_newPost";
    }


    // 글 작성
    @PostMapping("/newpost")
    public String postUser(@ModelAttribute PostForm post,
                           @AuthenticationPrincipal Member member,
                           HttpServletRequest request) throws IOException {
        log.info("--- PostController - /community/write -----------------------------------------");

        log.info(post.getContents());
        log.info(String.valueOf(post.getPhoto()));

        UploadFile uploadFile = fileStore.storeFile(post.getPhoto(), request);

        Post newPost = Post.builder()
                .contents(post.getContents())
                .uploadFile(uploadFile)
                .member(member)
                .postDate(LocalDateTime.now())
                .build();

        consumerService.savePost(newPost);
        return "redirect:/community";
    }


    /**
     * 글 -> 좋아요 누르기
     * **/
    @PostMapping("/community/{id}/likes")
    public String putLikes(@PathVariable String id, @AuthenticationPrincipal Member loginMember) {
        if(loginMember == null) {
            return "redirect:/login";
        }

        Long likeId = consumerService.putLikes(Long.valueOf(id), loginMember);
        System.out.println("Like Create No: "+likeId);

        return "redirect:/community/"+id;
    }


    /**
     * 댓글
     * **/
    // 댓글 작성
    @PostMapping("/community/{id}/comments/write")
    public String postComment(@PathVariable String id, @RequestParam String contents, 
                              @AuthenticationPrincipal Member loginMember) {
        if(loginMember == null) {
            return "redirected:/login";
        }
        consumerService.saveComment(id, contents, loginMember);
        return "redirect:/community/"+id;
    }


    // 댓글 수정
    @PutMapping("/comments/{id}/put")
    public String putComment(@PathVariable String id, @RequestBody CommentForm commentForm,
                             @AuthenticationPrincipal Member loginMember, Model model) {

        if(loginMember == null) {
            return "redirected:/login";
        }
        consumerService.updateComment(commentForm.getId(), commentForm.getContent());
        model.addAttribute("ACCESS", "SUCCESS");
        return "redirect:/community/"+commentForm.getPostId();
    }


    // 댓글 삭제
    @DeleteMapping("/comments/{id}/delete")
    public String deleteComment(@PathVariable Long id, Model model) {
        consumerService.deleteComment(id);
        model.addAttribute("ACESS", "SUCCESS");
        return "redirect:/community/";
    }

}