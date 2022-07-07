package com.devil.api.controller;

import com.devil.api.request.BoardCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BoardController {

    @GetMapping("/boards")
    public String get() {
        return "Hello World";
    }

    @PostMapping("/boards")
    public String create(@RequestBody BoardCreate params) {
        log.info("params={}", params.toString());
        return "Hello World";
    }
}
