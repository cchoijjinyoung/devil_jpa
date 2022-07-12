package com.devil.api.controller;

import com.devil.api.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {
    /**
     * 게시글 등록
     */
    @PostMapping("/posts")
    public Map<String, String> create(@RequestBody @Valid PostCreate request) {
        return Map.of();
    }
}
