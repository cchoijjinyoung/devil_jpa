package com.devil.api.controller;

import com.devil.api.request.PostCreate;
import com.devil.api.response.PostResponse;
import com.devil.api.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    /**
     * 게시글 등록
     */
    @PostMapping("/posts")
    public void create(@RequestBody @Valid PostCreate request) {
        postService.write(request);
    }

    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId) {
        return postService.get(postId);
    }
}
