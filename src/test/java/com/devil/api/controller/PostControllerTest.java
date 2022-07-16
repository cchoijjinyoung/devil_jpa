package com.devil.api.controller;

import com.devil.api.domain.Post;
import com.devil.api.repository.PostRepository;
import com.devil.api.request.PostCreate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach // 테스트 하나 실행하고나면 db를 clean 시킨다.
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("/posts 요청 시 title값은 필수다.")
    void validation() throws Exception {
        PostCreate request = PostCreate.builder()
                .title("")
                .content("내용입니다.")
                .build();
        String json = objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요."))
                .andDo(print());
    }

    @Test
    @DisplayName("/posts 요청 시 DB에 저장된다.")
    void savePost() throws Exception {
        PostCreate request = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();
        String json = objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());
        // then
        assertEquals(1L, postRepository.count());
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    void getPost() throws Exception {
        Post post = Post.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();
        postRepository.save(post);
        mockMvc.perform(get("/posts/{postId}", post.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.title").value("제목입니다."))
                .andExpect(jsonPath("$.content").value("내용입니다."))
                .andDo(print());
    }

    @Test
    @DisplayName("글 여러 개 조회")
    void getList() throws Exception {
        Post post1 = Post.builder()
                .title("제목1입니다.")
                .content("내용1입니다.")
                .build();
        postRepository.save(post1);

        Post post2 = Post.builder()
                .title("제목2입니다.")
                .content("내용2입니다.")
                .build();
        postRepository.save(post2);


        mockMvc.perform(get("/posts/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Matchers.is(2)))
                .andExpect(jsonPath("$[0].id").value(post1.getId()))
                .andExpect(jsonPath("$[0].title").value("제목1입니다."))
                .andExpect(jsonPath("$[0].content").value("내용1입니다."))
                .andDo(print());
    }
}