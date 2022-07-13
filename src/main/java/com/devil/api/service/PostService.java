package com.devil.api.service;

import com.devil.api.domain.Post;
import com.devil.api.repository.PostRepository;
import com.devil.api.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
