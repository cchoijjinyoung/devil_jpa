package com.devil.api.service;

import com.devil.api.domain.Post;
import com.devil.api.repository.PostRepository;
import com.devil.api.request.PostCreate;
import com.devil.api.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    // 생성자 인젝션 (@Autowired 해서 필드를 인젝션하는 것은 별로다. 스프링에서도 비추천한다.)
    public void write(PostCreate postCreate) {
        // postCreate는 Entity형태가 아니기 때문에 (일반클래스 -> Entity로 변형이 필요)
        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();
        postRepository.save(post);
    }

    public PostResponse get(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 글입니다."));
        PostResponse response = PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
        // Post를 PostResponse로 감싸는 작업이 Service에서 하는게 맞느냐? 하는 얘기도 있다.
        // Service를 두 개로 나눠서 구분해주는 것도 좋은 방법이다.
        return response;
    }

    public List<PostResponse> getList() {
        return postRepository.findAll().stream()
                .map(post -> PostResponse.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .build())
                .collect(Collectors.toList());
    }
}
