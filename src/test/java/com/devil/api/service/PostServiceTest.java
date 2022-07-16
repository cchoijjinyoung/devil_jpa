package com.devil.api.service;

import com.devil.api.domain.Post;
import com.devil.api.repository.PostRepository;
import com.devil.api.request.PostCreate;
import com.devil.api.response.PostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PostServiceTest {
    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void test1() throws Exception {
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();
        // when
        postService.write(postCreate);

        // then
        assertEquals(1l, postRepository.count());
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    void getTest() throws Exception {
        Post requestPost = Post.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();
        postRepository.save(requestPost);
        PostResponse response = postService.get(requestPost.getId());
        assertEquals("제목입니다.", response.getTitle());
        assertEquals("내용입니다.", response.getContent());
    }

    @Test
    @DisplayName("글 여러 개 조회")
    void getListTest() throws Exception {
        Post requestPost1 = Post.builder()
                .title("제목1입니다.")
                .content("내용1입니다.")
                .build();
        postRepository.save(requestPost1);

        Post requestPost2 = Post.builder()
                .title("제목2입니다.")
                .content("내용2입니다.")
                .build();
        postRepository.save(requestPost2);

        List<PostResponse> posts = postService.getList();
        assertEquals(2, posts.size());
        assertEquals("내용1입니다.", posts.get(0).getContent());
    }
}