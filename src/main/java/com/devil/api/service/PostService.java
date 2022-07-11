//package com.devil.api.service;
//
//import com.devil.api.repository.PostRepository;
//import com.devil.api.request.PostCreate;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@RequiredArgsConstructor
//public class PostService {
//
//    private final PostRepository postRepository;
//    // 생성자 인젝션 (@Autowired 해서 필드를 인젝션하는 것은 별로다. 스프링에서도 비추천한다.)
////    public void write(PostCreate postCreate) {
////        postRepository.save(postCreate);
////    }
